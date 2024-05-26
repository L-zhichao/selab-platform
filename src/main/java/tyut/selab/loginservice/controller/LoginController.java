package tyut.selab.loginservice.controller;


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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LoginController extends HttpServlet  {
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
            if(userService.findByUsername(userLoginReq.getUsername()) == 1 && userService.findByPassword(MD5util.encrypt(userLoginReq.getPassword())) == 1){
                //登录成功后根据username生成Token
                String token =  loginService.login(userLoginReq);
                UserLocal userLocal = userService.getUserLocal(userLoginReq.getUsername());
                //我们这里获取的UserLocal对象的groupId默认是null值
                userLocal.setToken(token);
                //将Token传给实体类对象UserLocal，并存入到ThreadLocal中，把该对象传给前端
                SecurityUtil.setUser(userLocal);
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
                //检验信息无误后，这时候发送验证码进行验证注册，发送验证码
                //验证验证码的相关逻辑，待完善
                String head = "实验室平台注册验证码信息";
                String verify = SecurityUtil.getRandom();
                String body = "亲爱的用户朋友，欢迎您来注册我们的系统！！！<br>" +
                        "这是您的验证码信息：<h3>" + verify + "<h3>。<br>" +
                        "验证码的有效期是30秒, 请在指定时间内填写验证信息<br>" +
                        "注意不要将自己的验证信息透露给别人";
                boolean flag = true;
                while(flag) {
                    try {
                        QQEmailService.qqemail(userRegisterDto.getEmail(), head, body);
                        flag = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
                //在这里可以判断验证码是否已经发送
                if(true == flag){
                    msg = "验证码发送失败";
                    return Result.error(STATUS_CODE_INNSER_ERROR,msg);
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
            msg = "用户邮箱输入格式错误，邮箱格式应满足qq邮箱的默认格式";
            return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
        }
        msg = "用户电话号码格式不正确";
        return Result.error(STATUS_CODE_NON_IMPLEMENTATION,msg);
    }
}