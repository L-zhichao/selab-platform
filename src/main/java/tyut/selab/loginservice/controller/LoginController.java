package tyut.selab.loginservice.controller;


import javax.mail.MessagingException;
import tyut.selab.loginservice.common.Constant;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.service.impl.LoginServiceImpl;
import tyut.selab.loginservice.service.impl.QQEmailService;
import tyut.selab.loginservice.service.impl.UserServiceImpl;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: LoginController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:12
 * @version: 1.0
 */
@WebServlet(name="LoginController",urlPatterns = {"/login","/register"})
public class LoginController extends HttpServlet implements Constant {
    EmailServiceImpl emailService = new EmailServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
    LoginServiceImpl loginService = new LoginServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);//因为前端发的关于登录的请求都是post类型，所以我们处理Get也用post的方式
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        register(req,resp);

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
        //获取前端发来的账号密码信息
        UserLoginReq userLoginReq = WebUtils.readJson(request, UserLoginReq.class);
        //验证账号密码信息
        //判断Token是否过期或者有没有Token，生成Token存入UserLocal对象中
        //将UserLocal对象传给前端，根据接口文档来做
        return null;


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
        Integer code = 400;
        String msg = "";
        Result result = new Result(400,null);
        //判断用户输入的用户名称和密码是否符合规范
        if(null == userRegisterDto.getUserName() || "".equals(userRegisterDto.getUserName())){
            msg = "用户名称不能为空";
            return Result.error(400,msg);
        }else if(null == userRegisterDto.getPassword() || "".equals(userRegisterDto.getPassword())){
            msg = "用户密码不能为空";
            return Result.error(400,msg);
        }
        if(1 == userService.findByUsername(userRegisterDto.getUserName())){
            msg = "该用户名已经被注册";
            return Result.error(400,msg);
        }
        if(QQEmailService.checkPhone(userRegisterDto.getPhone())){
            if(QQEmailService.checkEmail(userRegisterDto.getEmail())){
                if(emailService.queryNumForSameEmail(userRegisterDto.getEmail()) == MAX_SAME_MMAIL_REGISTER_NUM){
                    msg = "该邮箱已经被多次注册，请换一个新的邮箱再试试吧";
                    return Result.error(400,msg);
                }
                //检验信息无误后，这时候发送验证码进行验证注册，发送验证码
                //验证验证码的相关逻辑，待完善
                String head = "verification information";
                String verify = SecurityUtil.getRandom();
                String body = "Dear User, Welcome to register our system!!!<br>" +
                        "Here is your verification code:<h3>" + verify + "<h3>.<br>" +
                        "The validity period is 30 seconds, please complete the verification code within the specified period of time<br>";
                boolean flag = true;
                while(flag) {
                    try {
                        QQEmailService.qqemail(userRegisterDto.getEmail(), head, body);
                        flag = false;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                //在这里可以判断验证码是否已经发送
                if(true == flag){
                    msg = "验证码发送失败";
                    return Result.error(400,msg);
                }


                //验证码验证成功后，将对应的信息存入到数据库中，并且将邮箱注册信息存入到Email表中来记录邮箱注册次数
                loginService.register(userRegisterDto);
                emailService.save(new Email(emailService.getEmailNum() + 1, userRegisterDto.getEmail()));
                return Result.success(null);
            }
            msg = "用户邮箱输入格式错误";
            return Result.error(400,msg);
        }
        msg = "用户电话号码不正确";
        return Result.error(400,msg);
    }
}
