package tyut.selab.loginservice.controller;


import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.impl.LoginServiceImpl;
import tyut.selab.loginservice.utils.MD5util;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * @className: LoginController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:12
 * @version: 1.0
 */
@WebServlet(name="LoginController",urlPatterns = {"/login","/register"})
public class LoginController extends HttpServlet {
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

        UserLoginReq userLoginReq = WebUtils.readJson(request, UserLoginReq.class);


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


        String head = "登录验证码信息";
        String body = "验证码为Token";
        try {
            qqemail("2072349810@qq.com",head,body);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void qqemail(String QQmail,String head,String body) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("2072349810@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO,
                new InternetAddress[]{new InternetAddress(QQmail)});
        //new InternetAddress();设置同时发送多个好友
        // 设置邮件标题
        message.setSubject(head);
        // 设置邮件内容
        message.setText(body);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("2072349810@qq.com", "ldyckdzmsxgfdddf");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        int i = 0;
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("成功！");
        transport.close();
    }
    /**
     * 通过账号密码实现注册的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void registerByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收要注册的用户信息
        UserRegisterDto registUser = WebUtils.readJson(req, UserRegisterDto.class);
        //实现UserServiceImp类
        LoginServiceImpl userService = new LoginServiceImpl();
        // 调用服务层方法,将用户注册进入数据库
        int rows =userService.register(registUser);
        Result result =new Result(null,null);
        if(rows>0){
            result=result.success("null");
        }else{
            result =result.error(501,"fail to register");
        }
        WebUtils.writeJson(resp,result);
    }
    /**
     * 用户使用账密登录的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收用户请求参数
        // 获取要登录的用户名密码
        UserLoginReq inputUser = WebUtils.readJson(req, UserLoginReq.class);
        //实例化UserService
        LoginServiceImpl userService = new LoginServiceImpl();
        // 调用服务层方法,根据用户名查询数据库中是否有一个用户
        UserLoginReq loginUser =userService.findByUsername(inputUser.getUsername());

        Result result = new Result(null,null);

        if(null == loginUser){
            // 没有根据用户名找到用户,说明用户名有误
            result=result.error(502,"not found user by username");
        }else if(! loginUser.getPassword().equals(MD5util.encrypt(inputUser.getPassword()))){
            // 用户密码有误,
            result=result.error(503,"password failed");
        }else{
            // 登录成功
            result=result.success(null);
        }
        WebUtils.writeJson(resp,result);
    }

}
