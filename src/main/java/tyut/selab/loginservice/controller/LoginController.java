package tyut.selab.loginservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

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
        return null;
    }
    /**
     * 通过账号密码实现注册的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
//    protected void registerByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 接收要注册的用户信息
//        UserRegisterDto registUser = WebUtils.readJson(req, UserRegisterDto.class);
//        //实现UserServiceImp类
//        UserServiceImp userService = new UserServiceImp();
//        // 调用服务层方法,将用户注册进入数据库
//        int rows =userService.register(registUser);
//        Result result =new Result(null,null);
//        if(rows>0){
//            result=result.success("null");
//        }else{
//            result =result.error(501,"fail to register");
//        }
//        WebUtils.writeJson(resp,result);
//    }
    /**
     * 用户使用账密登录的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
//    protected void loginByUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        // 接收用户请求参数
//        // 获取要登录的用户名密码
//        UserLoginReq inputUser = WebUtils.readJson(req, UserLoginReq.class);
//        //实例化UserService
//        UserServiceImp userService = new UserServiceImp();
//        // 调用服务层方法,根据用户名查询数据库中是否有一个用户
//        UserLoginReq loginUser =userService.findByUsername(inputUser.getUsername());
//
//        Result result = new Result(null,null);
//
//        if(null == loginUser){
//            // 没有根据用户名找到用户,说明用户名有误
//            result=result.error(502,"not found user by username");
//        }else if(! loginUser.getPassword().equals(MD5util.encrypt(inputUser.getPassword()))){
//            // 用户密码有误,
//            result=result.error(503,"password failed");
//        }else{
//            // 登录成功
//            result=result.success(null);
//        }
//        WebUtils.writeJson(resp,result);
//    }

}
