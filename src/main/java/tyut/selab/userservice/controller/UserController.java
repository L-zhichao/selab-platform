package tyut.selab.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import tyut.selab.userservice.Dto.UserDto;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.service.impl.UserServiceImpl;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: UserController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 23:31
 * @version: 1.0
 */
@WebServlet(name="UserController",urlPatterns = "/user/*")
public class UserController extends HttpServlet {
    private UserVo userVo=new UserVo();
    private Result result=new Result(200,null);
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 2];
        String s = split[split.length - 1];
        if (methodName.equals("user")){
            if (s.equals("query")){
                try {
                    Result result = query(req, resp);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code",result.getCode());
                    jsonObject.put("msg", result.getMsg());
                    jsonObject.put("data", result.getData());
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if (s.equals("logout")) {
                try {
                    Result delete = logout(req, resp);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", delete.getCode());
                    jsonObject.put("msg", delete.getMsg());
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }else if (methodName.equals("role")){
            if (s.equals("update")){
                try {
                    Result delete = updateRole(req, resp);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("code", delete.getCode());
                    jsonObject.put("msg", delete.getMsg());
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write(jsonObject.toJSONString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        } else if (methodName.equals("queryById")) {
            try {
                Result result = queryById(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", result.getCode());
                jsonObject.put("msg", result.getMsg());
                jsonObject.put("data", result.getData());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (methodName.equals("group")) {
            try {
                Result result = groupUpdate(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", result.getCode());
                jsonObject.put("msg", result.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //super.doGet(req, resp);
    }

    private Result groupUpdate(HttpServletRequest req, HttpServletResponse resp) {
        userVo.setUserId(Long.valueOf(req.getParameter("userId")));
        userVo.setGroupId(Integer.valueOf(req.getParameter("groupId")));
        Integer update=userService.groupUpdate(userVo);
        if (update>0){
            Result success=result.success(null);
            return success;
        }
        Result error= result.error(400,"修改失败");
        return error;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if(methodName.equals("save")){
            try {
                Result delete = save(req, resp);;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", delete.getCode());
                jsonObject.put("msg", delete.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (methodName.equals("update")) {
            try {
                Result delete = update(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", delete.getCode());
                jsonObject.put("msg", delete.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        //super.doPost(req, resp);
    }



    /**
     *  查询小组所有用户
     *  param: cur size groupId  roleId 两个中有一个不为空或全为空，全为空则查询全部
     * @param request
     * @param response
     * @return list<User>
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        Integer groupId = Integer.valueOf((request.getParameter("groupId") == null) ? "0" : request.getParameter("groupId"));
        Integer roleId = Integer.valueOf((request.getParameter("roleId") == null) ? "0" : request.getParameter("roleId"));
        ArrayList<UserVo> groupList = new ArrayList<>();
        ArrayList<UserVo> userVoArrayList = new ArrayList<>();
        ArrayList<UserVo> list = new ArrayList<>();
        if (groupId!=0&&roleId==0){
            List<UserVo> userVos = userService.selectByGroupId(groupId);
            groupList.addAll(userVos);
            if (groupList.isEmpty()){
                Result error = result.error(400,"未查询到相关用户");
                return error;
            }
            Result success=result.success(groupList);
            return success;
        } else if (groupId==0&&roleId!=0) {
            List<UserVo> userVos=userService.selectByRoleId(roleId);
            userVoArrayList.addAll(userVos);
            if (userVoArrayList.isEmpty()){
                Result error = result.error(400,"未查询到相关用户");
                return error;
            }
            Result success=result.success(groupList);
            return success;
        }else if(groupId==0&&roleId==0){
            List<UserVo> userVos=userService.selectAll();
            list.addAll(userVos);
            if (list.isEmpty()){
                Result error = result.error(400,"未查询到相关用户");
                return error;
            }
            Result success=result.success(groupList);
            return success;
        }
        Result error = result.error(400,"未查询到相关用户");
        return error;
    }

    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        String userIdStr= split[split.length - 1];
        Long userId = Long.parseLong(userIdStr);
        UserVo selected = userService.selectByUserId(userId);
        if(selected!=null) {
            Result success=result.success(selected);
            return success;
        }
        Result error = result.error(400,"未查询到相关用户");
        return error;
    }

    /**
     *  增加用户
     *  param: UserDto
     * @param request
     * @param response
     * @return
     */
    private Result save(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String jsonData = request.getReader().lines().collect(Collectors.joining());
        UserVo userVo = JSON.parseObject(jsonData, UserVo.class);
        Integer save = userService.save(userVo);
        if (save>0){
            Result success=result.success(null);
            return success;
        }
        Result error= result.error(400,"新增用户失败");
        return error;
    }

    /**
     *  修改用户信息
     *  param: UserVo对象
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response){
        String jsonData = null;
        UserVo userVo=null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            jsonData = request.getReader().lines().collect(Collectors.joining());
            userVo = JSON.parseObject(jsonData, UserVo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Integer save = userService.update(userVo);
        if (save>0){
            Result success = result.success(null);
            return success;
        }
        Result error= result.error(400,"修改用户信息失败");
        return error;
    }

    /**
     *  注销用户
     *  param: 要删除用户的userId
     * @param request
     * @param response
     * @return
     */
    private Result logout(HttpServletRequest request,HttpServletResponse response){
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer delete = userService.delete(userId);
        if (delete>0){
            Result success = result.success(null);
            return success;
        }
        Result error = result.error(400,"未能删除成功");
        return error;
    }

    /**
     *  修改用户权限
     *  param: UserRoleDto
     * @param request
     * @param response
     * @return
     */
    private Result updateRole(HttpServletRequest request, HttpServletResponse response){
        Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        Long userId = Long.valueOf(request.getParameter("userId"));
        //Date date = new Date();
        userVo.setUserId(userId);
        //userVo.setGroupName();
        userVo.setRoleId(roleId);
        //userVo.setRoleName();
        Integer save = userService.updateUserRole(userVo);
        if (save>0){
           Result success = result.success(null);
           return success;
        }
        Result error = result.error(400,"没有权限修改用户权限");
        return error;
    }

}
