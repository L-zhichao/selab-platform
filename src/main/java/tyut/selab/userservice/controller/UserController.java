package tyut.selab.userservice.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.Dto.UserDto;
import tyut.selab.userservice.common.Constant;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.ServiceImpl.UserServiceImpl;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import static com.alibaba.druid.sql.dialect.oracle.ast.expr.OracleSizeExpr.Unit.T;
import static jdk.jfr.internal.consumer.EventLog.update;

/**
 * @className: UserController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 23:31
 * @version: 1.0
 */


@WebServlet(name = "UserController",value = "/user/*")
public class UserController extends HttpServlet {



    //private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathGet = req.getPathInfo();
//        System.out.println(pathGet);
        System.out.println("doGet");
        //判断是否为queryById
        String[] split = pathGet.split("/");
        String modeName = split[1];
//        System.out.println(modeName);
        if(pathGet.equals("/query")){
            query(req, resp);

        }else if(modeName.equals("queryById")){//如何实现对动态url的检测?

            queryById(req, resp);
       }


        PrintWriter out = resp.getWriter();
        out.write("get");
        out.flush();
        out.close();
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathPost = req.getPathInfo();


        if(pathPost.equals("/save")){
            try {
                save(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if(pathPost.equals("/update")){
            update(req, resp);
        }
        PrintWriter out = resp.getWriter();
        out.write("post");
        out.flush();
        out.close();
        super.doPost(req, resp);
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        System.out.println("Hello World");
        resp.getWriter().write("user");



       /* String uri = req.getRequestURI();
        System.out.println("uri:" + uri);

        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("methodName:" + methodName);

        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
            resp.getWriter().write("update");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }*/
    }


    /**
     *  查询小组所有用户
     *
     *  param: cur size groupId  roleId 两个中有一个不为空或全为空，全为空则查询全部
     * @param request
     * @param response
     * @return list<User>
     */


    private Result query(HttpServletRequest request,HttpServletResponse response){
        String groupId = request.getParameter("groupId");
        String roleId = request.getParameter("roleId");
        List<UserVo> usersByGroupId = userService.selectByGroupId(Integer.valueOf(groupId));
        return null;
    }

    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("doQueryById");
        String path = request.getPathInfo();
        String[] split = path.split("/");
        String userId = split[split.length-1];
        System.out.println(userId);
        UserVo userVo = userService.selectByUserId(Long.valueOf(userId));

        return Result.success(userVo);
    }

    /**
     *  增加用户
     *  param: UserDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
        System.out.println("doSave");
        request.setCharacterEncoding("UTF-8");
        String jsonData = request.getReader().lines().collect(Collectors.joining());
        UserVo userVo = JSON.parseObject(jsonData, UserVo.class);
        int insert = userService.save(userVo);








        return null;}

    /**
     *  修改用户信息
     *  param: UserVo对象
     * @param request
     * @param response
     * @return post 无返回参数
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Update");
        // 获取登录用户roleId
        Integer loginRoleId = Integer.valueOf(request.getParameter("roleId"));
        if (loginRoleId.equals(2)) {
            UserVo userVo = new UserVo();
            userService.updateUser(userVo);
            return Result.success(null);
        }
        return null;
    }

    /**
     *  注销用户
     *  param: 要删除用户的userId
     * @param request
     * @param response
     * @return
     */
    private Result logout(HttpServletRequest request,HttpServletResponse response){
        //请求路径 user/logout,无返回数据

        // token 获得目前登录用户roleId
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        //判断是否为管理员
        if (roleId.equals(2)) {
            //获取被注销用户userId
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            userService.delete(userId);
            return Result.success(null);
        }
        else {
            return Result.error(null, null);
        }
    }

    /**
     *  修改用户权限
     *  param: UserRoleDto
     * @param request
     * @param response
     * @return
     */
    private Result updateRole(HttpServletRequest request, HttpServletResponse response){
        //请求路径 user/role/update

        // token 获得目前登录用户roleId ？？？
       Integer roleId = Integer.valueOf(request.getParameter("roleId"));

        //使用Dto，常量接口  ？？？
        //判断是否为超级管理员
        if (roleId.equals(1)) {
            //调用userService方法，封装被修改用户
            UserVo userVo = null;
            userVo.setUserId(Long.valueOf(request.getParameter("id")));
            Integer userUpdateVo = userService.updateUserRole(userVo);
            return Result.success(null);
        }
        else {
            return Result.error(null, null);

        }

    }

}
}