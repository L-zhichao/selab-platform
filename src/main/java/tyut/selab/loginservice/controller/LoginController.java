package tyut.selab.loginservice.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.service.impl.UserServiceImpl;
import tyut.selab.loginservice.utils.TokenTools;
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
        //
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

    @WebServlet("/LoginServlet")
    public class LoginServlet extends HttpServlet{
        private static final long serialVersionUID = 1L;

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            TokenTools tokenTools =new TokenTools();
            String userName = request.getParameter("userName");
            String token = request.getParameter("token");
            tokenTools.createToken(request,userName);


            if (tokenTools.judgeTokenIsEqual(request,token,userName)) {
                //main.jsp文件为要跳转的jsp界面.
                request.getRequestDispatcher("main.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }
    }
}
