package tyut.selab.userservice.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.Dto.UserDto;
import tyut.selab.userservice.common.Constant;
import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
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

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathGet = req.getPathInfo();
        System.out.println(pathGet);
        String methodName = pathGet.substring(pathGet.indexOf("user/")+2);
        System.out.println("methodName = " + methodName);

        if(methodName.equals("query")){
            Result query = query(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", query.getCode());
            jsonObject.put("msg", query.getMsg());
            jsonObject.put("data",query.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());

        }else if(methodName.equals("queryById")){
            Result queryById = queryById(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", queryById.getCode());
            jsonObject.put("msg", queryById.getMsg());
            jsonObject.put("data",queryById.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
       } else if (methodName.equals("logout")) {
            Result logout = logout(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", logout.getCode());
            jsonObject.put("msg", logout.getMsg());
            jsonObject.put("data",logout.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
        } else if (methodName.equals("role/update")) {
            Result updateRole = updateRole(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", updateRole.getCode());
            jsonObject.put("msg", updateRole.getMsg());
            jsonObject.put("data",updateRole.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        String pathPost = req.getPathInfo();
        System.out.println("pathPost:"+pathPost);


        if(pathPost.equals("/save")){
            try {

                Result save = save(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", save.getCode());
                jsonObject.put("msg", save.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else if(pathPost.equals("/update")){
            update(req, resp);
        }

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
        String userId = request.getParameter("userId");
        List<UserVo>  usersByGroupId = new ArrayList<>();
        List<UserVo> ResultList = new ArrayList<>();
        UserVo userByUserId = null;
        if (userId != null && groupId != null){
            userByUserId = userService.selectByUserId(Long.valueOf(userId));
            ResultList.add(userByUserId);
        }else {
            if (groupId != null && userId == null) {
                usersByGroupId = userService.selectByGroupId(Integer.valueOf(groupId));
                ResultList.addAll(usersByGroupId);

            }
            if (userId != null && groupId == null ) {
                userByUserId = userService.selectByUserId(Long.valueOf(userId));
                ResultList.add(userByUserId);
            }
        }
        return Result.success(ResultList);
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
//        System.out.println(userId);
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
        if (insert == 1) {
            return Result.error(400, "添加失败");
        } else {
            return Result.success(insert);
        }
    }



    /**
     *  修改用户信息 Post
     *  param: UserVo对象
     * @param request
     * @param response
     * @return post 无返回参数
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("update");
        String jsonData = request.getReader().lines().collect(Collectors.joining());
        UserVo userVo = JSON.parseObject(jsonData, UserVo.class);
        int rows = userService.updateUser(userVo);
        if (rows >= 1) {
            return Result.success(rows);
        }
        return Result.error(400,"操作失败");
    }

        /**
         *  注销用户 Get
         *  param: 要删除用户的userId
         * @param request
         * @param response
         * @return
         * 请求路径 user/logout
         */
        private Result logout (HttpServletRequest request, HttpServletResponse response){

            Integer userId = Integer.valueOf(request.getParameter("userId"));
            Integer rows = userService.delete(userId);
            if (rows>=1) {
                return Result.success(rows);
            }
            return Result.error(null, "操作失败");
        }


        /**
         *  修改用户权限 Get
         *  param: UserRoleDto
         * @param request
         * @param response
         * @return
         * 请求路径 :user/role/update
         */
        private Result updateRole (HttpServletRequest request, HttpServletResponse response){

            UserVo userVo = new UserVo();
            userVo.setUserId(Long.valueOf(request.getParameter("userId")));
            userVo.setRoleId(Integer.valueOf(request.getParameter("roleId")));
            int rows = userService.updateUserRole(userVo);
            if (rows>=1) {
                return Result.success(rows);
            }
            return Result.error(400, "操作失败");
        }



        /**
        * Description: 修改用户所属小组
        * @param request
         * @param response
        * @return Result
        */
        private Result updateGroup(HttpServletRequest request, HttpServletResponse response){
            UserVo userVo = new UserVo();
            userVo.setUserId(Long.valueOf(request.getParameter("userId")));
            userVo.setGroupId(Integer.valueOf(request.getParameter("groupId")));
            int rows = userService.updateGroup(userVo);
            if (rows >= 1) {
                return Result.success(rows);
            }
                return Result.error(400,"操作失败");
        }

}
