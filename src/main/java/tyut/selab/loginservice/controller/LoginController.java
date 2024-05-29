package tyut.selab.loginservice.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.service.impl.LoginServiceImpl;
import tyut.selab.loginservice.service.impl.QQEmailService;
import tyut.selab.loginservice.service.impl.UserServiceImpl;
import tyut.selab.loginservice.utils.MD5util;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import java.io.IOException;
import java.sql.SQLException;

import static tyut.selab.loginservice.common.Constant.*;

/**
 * @className: LoginController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:12
 * @version: 1.0
 */
@WebServlet(name="LoginController",urlPatterns = {"/login","/register"})
public class LoginController extends HttpServlet {
    EmailServiceImpl emailService = new EmailServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    LoginServiceImpl loginService = new LoginServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);//因为前端发的关于登录的请求都是post类型，所以我们处理Get也用post的方式
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String s = split[split.length - 1];
        if("login".equals(s)){
            WebUtils.writeJson(resp,login(req,resp));
        }else if("register".equals(s)){
            WebUtils.writeJson(resp,register(req,resp));
        }
    }
    /**
     *  登录操作
     *  param: UserLoginReq
     *  return: UserLocal
     * @param request
     * @param response
     * @return
     */
    private Result login(HttpServletRequest request, HttpServletResponse response){
        //判断账号是否已经注册过，判断密码是否正确
        UserLoginReq userLoginReq = WebUtils.readJson(request, UserLoginReq.class);
        String msg = "";
        //判断输入的用户名密码是否为空，不能省略不然后面可能会出现空指针
        if(null == userLoginReq.getUsername() || "".equals(userLoginReq.getUsername())){
            msg = "用户名称不能为空";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }else if(null == userLoginReq.getPassword() || "".equals(userLoginReq.getPassword())){
            msg = "用户密码不能为空";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        if(false == QQEmailService.checkUserName(userLoginReq.getUsername())){
            msg = "用户名6到12个字符，可以包含中文、大小写字母、和数字，请检查自己的用户名格式是否正确";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }else if(false == QQEmailService.checkPassword(userLoginReq.getPassword())){
            msg = "密码6到12个字符，其中至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符，不可以是中文,请检查自己的用户名格式是否正确";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        //在数据库中查找是否有该账号的注册记录，如果有则登录成功，并生成对应的Token传给前端
        try {
            if(1 == userService.findByUsername(userLoginReq.getUsername()) && 1 == userService.findByPassword(MD5util.encrypt(userLoginReq.getPassword()))){
                //登录成功后根据username生成Token
                String token = loginService.login(userLoginReq);
                UserLocal userLocal = userService.getUserLocal(userLoginReq.getUsername());
                userLocal.setToken(token);
                return Result.success(userLocal);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器内部问题";
            return Result.error(STATUS_CODE_INNSER_ERROR,msg);
        }
        msg = "账号或密码错误，请重新输入";
        return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
    }

    /**
     * 注册操作
     * param: userservice模块User对象 直接调用另一个模块service层对应方法就好
     * @param request
     * @param response
     * @return
     */
    private Result register(HttpServletRequest request,HttpServletResponse response){
        //判断用户输入的QQ邮箱和电话号码正不正确
        UserRegisterDto userRegisterDto = WebUtils.readJson(request, UserRegisterDto.class);
        String msg = "";
        //判断用户输入的用户名称和密码是否符合规范
        if(null == userRegisterDto.getUserName() || "".equals(userRegisterDto.getUserName())){
            msg = "用户名称不能为空";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }else if(null == userRegisterDto.getPassword() || "".equals(userRegisterDto.getPassword())){
            msg = "用户密码不能为空";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        if(false == QQEmailService.checkUserName(userRegisterDto.getUserName())){
            msg = "用户名6到12个字符，可以包含中文、大小写字母、和数字，请检查自己的用户名格式是否正确";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }else if(false == QQEmailService.checkPassword(userRegisterDto.getPassword())){
            msg = "密码6到12个字符，其中至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符，不可以是中文,请检查自己的用户名格式是否正确";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        try {
            if(1 == userService.findByUsername(userRegisterDto.getUserName())){
                msg = "该用户名已经被注册";
                return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器内部问题";
            return Result.error(STATUS_CODE_INNSER_ERROR,msg);
        }
        if(QQEmailService.checkPhone(userRegisterDto.getPhone())){
            if(QQEmailService.checkEmail(userRegisterDto.getEmail())){
                try {
                    if(emailService.queryNumForSameEmail(userRegisterDto.getEmail()) == MAX_SAME_MMAIL_REGISTER_NUM){
                        msg = "该邮箱已经被多次注册，请换一个新的邮箱再试试吧";
                        return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    msg = "服务器内部问题";
                    return Result.error(STATUS_CODE_INNSER_ERROR,msg);
                }
                //将验证码存入到Session中Session的有效期是60秒
                HttpSession session = request.getSession();
                String head = "平台注册验证码信息";
                String type = "QQ邮箱注册平台";
                //如果我们的session是新创建的那么就重新执行一遍发验证码的逻辑
                if(session.isNew()) {
                    session.setMaxInactiveInterval(60);
                    String verify = SecurityUtil.getRandom();
                    String body = String.format(VERIFICATION_HTML_TEXT,head,userRegisterDto.getUserName(),type,verify,"注册");
                    boolean flag = true;
                    try {
                        QQEmailService.qqemail(userRegisterDto.getEmail(), head, body);
                        flag = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //在这里可以判断验证码是否已经发送
                    if(flag){
                        msg = "验证码发送失败";
                        return Result.error(STATUS_CODE_INNSER_ERROR,msg);
                    }
                    session.setAttribute("verify", verify);
                    return Result.success(null);
                }else{
                    if (null != session.getAttribute("verify") && null != request.getHeader("verify") && !"".equals(request.getHeader("verify"))) {
                        if(!session.getAttribute("verify").equals(request.getHeader("verify"))){
                            msg = "验证码不正确，请重新输入";
                            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
                        }
                        //验证码验证成功后，将对应的信息存入到数据库中，并且将邮箱注册信息存入到Email表中来记录邮箱注册次数
                        try {
                            loginService.register(userRegisterDto);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            msg = "服务器内部问题";
                            return Result.error(STATUS_CODE_INNSER_ERROR,msg);
                        }
                        try {
                            emailService.save(new Email(emailService.getEmailNum() + 1, userRegisterDto.getEmail()));
                        } catch (Exception e) {
                            e.printStackTrace();
                            msg = "服务器内部问题";
                            return Result.error(STATUS_CODE_INNSER_ERROR,msg);
                        }
                        return Result.success(null);
                    }
                    msg = "请输入正确的验证码";
                    return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
                }
            }
            msg = "用户邮箱输入格式错误，邮箱格式应满足qq邮箱的默认格式";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        msg = "用户电话号码格式不正确";
        return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
    }
}
//guigushaobo  Ws858585 3388532526@qq.com 13111190851 0