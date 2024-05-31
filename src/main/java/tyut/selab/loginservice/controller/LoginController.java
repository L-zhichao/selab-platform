package tyut.selab.loginservice.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tyut.selab.loginservice.common.Constant;
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

import static javax.swing.text.html.HTML.Tag.HEAD;
import static tyut.selab.loginservice.common.Constant.*;

/**
 * @className: LoginController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:12
 * @version: 1.0
 */
@WebServlet(name="LoginController",urlPatterns = {"/login","/register","/sendEmail"})
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
        }else if("sendEmail".equals(s)){
            WebUtils.writeJson(resp,sendEmail(req,resp));
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
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        response.setHeader("Access-Control-Allow-Headers","Content-Type,X-Requested-With");
        // 写一个发送邮件的剪口，当前前段点击发送验证码按钮后会访问到的接口
        //判断账号是否已经注册过，判断密码是否正确
        UserLoginReq userLoginReq = WebUtils.readJson(request, UserLoginReq.class);
        String msg = "";
        //判断输入的用户名密码是否为空，不能省略不然后面可能会出现空指针
        if(null == userLoginReq.getUsername() || "".equals(userLoginReq.getUsername())){
            msg = "用户名称不能为空";
            return Result.error(ACCOUNT_FORMAT_ERROR,msg);
        }else if(null == userLoginReq.getPassword() || "".equals(userLoginReq.getPassword())){
            msg = "用户密码不能为空";
            return Result.error(PASSWORD_FORMAT_ERROR,msg);
        }
        if(false == QQEmailService.checkUserName(userLoginReq.getUsername())){
            msg = "用户名6到12个字符，可以包含中文、大小写字母、和数字，请检查自己的用户名格式是否正确";
            return Result.error(ACCOUNT_FORMAT_ERROR,msg);
        }else if(false == QQEmailService.checkPassword(userLoginReq.getPassword())){
            msg = "密码6到12个字符，其中至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符，不可以是中文,请检查自己的用户名格式是否正确";
            return Result.error(PASSWORD_FORMAT_ERROR,msg);
        }
        //在数据库中查找是否有该账号的注册记录，如果有则登录成功，并生成对应的Token传给前端
        try {
            if( (1 == userService.findByUsername(userLoginReq.getUsername()))){
                if(MD5util.encrypt(userLoginReq.getPassword()).equals(userService.getUserByUsername(userLoginReq.getUsername()).getPassword())){
                    //登录成功后根据username生成Token
                    String token = loginService.login(userLoginReq);
                    UserLocal userLocal = userService.getUserLocal(userLoginReq.getUsername());
                    userLocal.setToken(token);
                    return Result.success(userLocal);
                }
                msg = "密码错误，请输入正确的密码";
                return Result.error(PASSWORD_FORMAT_ERROR,msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器内部错误";
            return Result.error(STATUS_CODE_INNSER_ERROR,msg);
        }
        msg = "账号错误，请输入正确的账号";
        return Result.error(ACCOUNT_OR_PASSWORD_ERROR,msg);

    }

    /**
     *  请求体中有 email   返回 如果说发送验证码失败 返回 code 500001
     * @param request
     * @param response
     * @return
     */
    public Result sendEmail(HttpServletRequest request,HttpServletResponse response){
        String msg = "";
        HttpSession session = request.getSession();
        Email em = WebUtils.readJson(request,Email.class);
        String email = em.getEmail();
        if(null == email || "".equals(email)) {
            msg = "邮箱信息不能为空";
            return Result.error(MAIL_FORMAT_ERROR_CODE, msg);
        }else if(false == QQEmailService.checkEmail(email)){
            msg = "用户邮箱输入格式错误，邮箱格式应满足qq邮箱的默认格式";
            return Result.error(MAIL_FORMAT_ERROR_CODE,msg);
        }else {
            try {
                if(3 == emailService.queryNumForSameEmail(email)){
                    msg = "该邮箱已经被多次注册，请换一个新的邮箱再试试吧";
                    return Result.error(MAIL_FORMAT_ERROR_CODE,msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg = "服务器内部异常";
                return Result.error(STATUS_CODE_INNSER_ERROR,msg);
            }
        }
        //防止直接通过url路径进入操作，并且session的这两个参数都在register接口中进行
        if( session.isNew() && null == session.getAttribute("verify")) {
            session.setMaxInactiveInterval(60);
            String verify = SecurityUtil.getRandom();
            String body = String.format(VERIFICATION_HTML_TEXT, HEAD,"", TYPE, verify, "注册");
            boolean flag = true;
            try {
                QQEmailService.qqemail(email, Constant.HEAD, body);
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            //在这里可以判断验证码是否已经发送
            if (flag) {
                msg = "验证码发送失败";
                return Result.error(FAILED_SEND_ERROR_CODE, msg);
            }
            session.setAttribute("verify", verify);
            return Result.success(null);
        }
        msg = "请等待60秒后，再进行发送";
        return Result.error(FAILED_SEND_ERROR_CODE,msg);
    }
    /**
     * 注册操作
     * param: userservice模块User对象 直接调用另一个模块service层对应方法就好
     * @param request
     * @param response
     * @return
     */
    private Result register(HttpServletRequest request,HttpServletResponse response){

        //获取到验证码发送的时间戳
        UserRegisterDto userRegisterDto = WebUtils.readJson(request, UserRegisterDto.class);
        String msg = "";
        //判断用户输入的用户名称和密码是否符合规范
        if(null == userRegisterDto.getUserName() || "".equals(userRegisterDto.getUserName())){
            msg = "用户名称不能为空";
            return Result.error(ACCOUNT_FORMAT_ERROR,msg);
        }else if(null == userRegisterDto.getPassword() || "".equals(userRegisterDto.getPassword())){
            msg = "用户密码不能为空";
            return Result.error(PASSWORD_FORMAT_ERROR,msg);
        }
        if(false == QQEmailService.checkUserName(userRegisterDto.getUserName())){
            msg = "用户名6到12个字符，可以包含中文、大小写字母、和数字，请检查自己的用户名格式是否正确";
            return Result.error(ACCOUNT_FORMAT_ERROR,msg);
        }else if(false == QQEmailService.checkPassword(userRegisterDto.getPassword())){
            msg = "密码6到12个字符，其中至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符，不可以是中文,请检查自己的用户名格式是否正确";
            return Result.error(PASSWORD_FORMAT_ERROR,msg);
        }
        try {
            if(1 == userService.findByUsername(userRegisterDto.getUserName())){
                msg = "该用户名已经被注册";
                return Result.error(ACCOUNT_FORMAT_ERROR,msg);
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
                        return Result.error(MAIL_FORMAT_ERROR_CODE,msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    msg = "服务器内部问题";
                    return Result.error(STATUS_CODE_INNSER_ERROR,msg);
                }
                //将验证码存入到Session中Session的有效期是60秒
                HttpSession session = request.getSession();
                //如果我们的session是新创建的那么就重新执行一遍发验证码的逻辑
                if(!session.isNew() && null != session.getAttribute("verify")){
                    if (null != userRegisterDto.getIdentify() && !"".equals(userRegisterDto.getIdentify())) {
                        if(!session.getAttribute("verify").equals(userRegisterDto.getIdentify())){
                            msg = "验证码不正确，请重新输入";
                            return Result.error(VERIFY_INFO_ERROR,msg);
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
                        session.removeAttribute("verify");
                        session.invalidate();
                        return Result.success(null);
                    }
                    msg = "验证码不能为空";
                    return Result.error(VERIFY_INFO_ERROR,msg);
                }
                session.removeAttribute("verify");
                session.invalidate();
                msg = "请先发送验证码";
                return Result.error(FAILED_SEND_ERROR_CODE,msg);
            }
            msg = "用户邮箱输入格式错误，邮箱格式应满足qq邮箱的默认格式";
            return Result.error(MAIL_FORMAT_ERROR_CODE,msg);
        }
        msg = "用户电话号码格式不正确";
        return Result.error(PHONENUM_FORMAT_ERROR,msg);
    }
}