package tyut.selab.loginservice.controller;

import jakarta.mail.MessagingException;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
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
/**
 * @className: LoginController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:12
 * @version: 1.0
 */
@WebServlet(name="LoginController",urlPatterns = {"/login","/register"})
public class LoginController extends HttpServlet {
    EmailServiceImpl serviceImpl = new EmailServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();
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
        Result result = new Result(400,null);
        String msg = "";
        if(QQEmailService.checkPhone(userRegisterDto.getPhone())){
            if(QQEmailService.checkEmail(userRegisterDto.getEmail())){
                //检验信息无误后，这时候发送验证码进行验证注册
                String head = "";
                String verify = SecurityUtil.getRandom();
                String body = "登录需要的验证码为:" + verify;
                try {
                    QQEmailService.qqemail(userRegisterDto.getEmail(),head,body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //注册完成后，将对应的信息存入到数据库中
            }
            msg = "用户邮箱输入格式错误";
            result.error(400,msg);
            return result;
        }
        msg = "用户电话号码不正确";
        result.error(400,msg);
        return result;
    }
}
