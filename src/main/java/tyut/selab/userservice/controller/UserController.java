package tyut.selab.userservice.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.Dto.UserDto;
import tyut.selab.userservice.common.Constant;
import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.domain.Page;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.ServiceImpl.UserServiceImpl;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
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
import java.util.*;
import java.util.List;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathGet = req.getPathInfo();
        System.out.println(pathGet);
        //判断是否为queryById
        String[] split = pathGet.split("/");
        String modeName = split[1];
        System.out.println("modeName:" + modeName);

        if(modeName.equals("query")){
            Result query = query(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", query.getCode());
            jsonObject.put("msg", query.getMsg());
            jsonObject.put("data",query.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());

        }else if(modeName.equals("queryById")){
            Result queryById = queryById(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", queryById.getCode());
            jsonObject.put("msg", queryById.getMsg());
            jsonObject.put("data",queryById.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
        } else if (modeName.equals("logout")) {
            Result logout = logout(req, resp);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", logout.getCode());
            jsonObject.put("msg", logout.getMsg());
            jsonObject.put("data",logout.getData());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
        } else if (modeName.equals("role")) {
            String method = pathGet.substring(pathGet.lastIndexOf("/")+1);
            System.out.println("method:" + method);
            if (method.equals("update")) {
                Result updateRole = updateRole(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", updateRole.getCode());
                jsonObject.put("msg", updateRole.getMsg());
                jsonObject.put("data",updateRole.getData());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            }
        }else if (modeName.equals("group")) {
            String method = pathGet.substring(pathGet.lastIndexOf("/")+1);
            System.out.println("method:" + method);
            if (method.equals("update")) {
                Result updateGroup = updateGroup(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", updateGroup.getCode());
                jsonObject.put("msg", updateGroup.getMsg());
                jsonObject.put("data",updateGroup.getData());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        String pathPost = req.getPathInfo();
        System.out.println(pathPost);
        //判断是否为queryById
        String[] split = pathPost.split("/");
        String modeName = split[1];
        if(modeName.equals("save")){
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
        }
        else if(modeName.equals("update")){
            Result update = update(req, resp);
            System.out.println("111");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", update.getCode());
            jsonObject.put("msg", update.getMsg());
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write(jsonObject.toJSONString());
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
        //条件查询
        Integer cur = Integer.valueOf((request.getParameter("cur") == null) ? "1" : request.getParameter("cur"));
        Integer size = Integer.valueOf((request.getParameter("size") == null) ? "5" : request.getParameter("size"));


        Page<UserVo> page = new Page<>();



        UserVo userByUserId = null;
        if (userId != null && groupId != null){
            page = userService.selectByUserId(Long.valueOf(userId));
        }else {
            if (groupId != null && userId == null) {
                page = userService.selectByGroupId(Integer.valueOf(groupId),cur,size);

            } else if (userId != null && groupId == null ) {
                page = userService.selectByUserId(Long.valueOf(userId));

            } else if (userId == null && groupId == null){
                page = userService.queryAll(cur,size);
            }
        }
        page.setCur(cur);
        page.setSize(size);


        if(page.getData() == null){
            return Result.error(400,"查询失败");
        }else {
            return Result.success(page);
        }
    }



    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String path = request.getPathInfo();
        String[] split = path.split("/");
        String userId = split[split.length-1];
        Page<UserVo> page = new Page<UserVo>();
        List<UserVo> Uservo = new ArrayList<>();
        UserVo user = new UserVo();
        page = userService.selectByUserId(Long.valueOf(userId));
        Uservo = page.getData();
        user = Uservo.get(0);
        return Result.success(user);
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
        if (insert == 0) {
            return Result.error(400, "添加失败,请检查添加格式");
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
        UserVo userVo = new UserVo();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String jsonData = request.getReader().lines().collect(Collectors.joining());
        userVo = JSON.parseObject(jsonData, UserVo.class);
        int rows = userService.updateUser(userVo);
        if (rows == -1) {
            return Result.error(400,"所修改信息的格式不正确，操作失败");
        } else if (rows == 0) {
            return Result.error(400,"您没有操作权限，操作失败");
        }
        return Result.success(rows);
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
            System.out.println("logout");
            Integer userId = Integer.valueOf((request.getParameter("userId")));
            Integer rows = userService.delete(userId);
            if (rows == 0) {
                return  Result.error(400, "您没有操作权限，操作失败");
            } else if (rows == -1) {
                return  Result.error(400,"该用户已经被删除");
            }
            return Result.success(rows);
        }


        /**
         *  修改用户权限 Get
         *  param: UserRoleDto
         * @param request
         * @param response
         * @return
         * 请求路径 :user/role/update
         */
        private Result updateRole (HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("updateRole");
            UserVo userVo = new UserVo();
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            userVo = JSON.parseObject(jsonData, UserVo.class);
            int rows = userService.updateUserRole(userVo);
            if (rows == -1) {
                return Result.error(400, "请填写正确的用户权限标识，操作失败");
            } else if (rows == 0) {
                return Result.error(400,"您没有操作权限，操作失败");
            }
            return Result.success(rows);
        }



        /**
        * Description: 修改用户所属小组
        * @param request
         * @param response
        * @return Result
        */
        private Result updateGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
            System.out.println("updateGroup");
            UserVo userVo = new UserVo();
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            userVo = JSON.parseObject(jsonData, UserVo.class);
            int rows = userService.updateGroup(userVo);
            if (rows == -1) {
                return Result.error(400,"不存在该小组，操作失败");
            } else if (rows == 0) {
                return Result.error(400,"您没有操作权限，操作失败");
            } else if (rows == -2) {
                return Result.error(400,"该用户不存在，请重新操作");
            }
            return Result.success(rows);

        }

}
