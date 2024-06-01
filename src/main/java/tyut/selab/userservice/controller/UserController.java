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
    //private Result result=new Result(200,null);
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
     * success
     * @param req
     * @param resp
     * @return
     */
    private Result groupUpdate(HttpServletRequest req, HttpServletResponse resp) {
        String userIdStr = req.getParameter("userId");
        Long userId = null;
        boolean isInteger = true;
        for (int i = 0; i < userIdStr.length(); i++) {
            if (!Character.isDigit(userIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }
        if (isInteger) {
            userId = Long.parseLong(userIdStr);
            userVo.setUserId(userId);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        String groupIdStr = req.getParameter("groupId");
        Integer groupId = null;
        isInteger = true;
        for (int i = 0; i < groupIdStr.length(); i++) {
            if (!Character.isDigit(groupIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }
        if (isInteger) {
            groupId = Integer.parseInt(groupIdStr);
            userVo.setGroupId(groupId);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        userVo.setUserId(userId);
        //userVo.setUserId(Long.valueOf(req.getParameter("userId")));
        //userVo.setGroupId(Integer.valueOf(req.getParameter("groupId")));
        Integer update=userService.groupUpdate(userVo);
        if (update>0){
            return Result.success(null);
        }
        return Result.error(50005,"修改用户信息失败");
    }


    /**
     *  查询小组所有用户
     *  success
     *  param: cur size groupId  roleId 两个中有一个不为空或全为空，全为空则查询全部
     * @param request
     * @param response
     * @return list<User>
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        Integer groupId=0;
        String groupIdStr=null;
        if (request.getParameter("groupId")==null){
            groupId=0;
        } else if (request.getParameter("groupId")!=null) {
            groupIdStr=request.getParameter("groupId");
            boolean isInteger = true;
            for (int i = 0; i < groupIdStr.length(); i++) {
                if (!Character.isDigit(groupIdStr.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (isInteger) {
                groupId = Integer.parseInt(groupIdStr);
            } else {
                return Result.error(50009,"输入信息错误");
            }
        }
        Integer roleId=0;
        String roleIdStr=null;
        if (request.getParameter("roleId")==null){
            roleId=0;
        } else if (request.getParameter("roleId")!=null) {
            roleIdStr=request.getParameter("roleId");
            boolean isInteger = true;
            for (int i = 0; i < roleIdStr.length(); i++) {
                if (!Character.isDigit(roleIdStr.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (isInteger) {
                roleId = Integer.parseInt(roleIdStr);
            } else {
                return Result.error(50009,"输入信息错误");
            }
        }
        //Integer groupId = Integer.valueOf((request.getParameter("groupId") == null) ? "0" : request.getParameter("groupId"));
        //Integer roleId = Integer.valueOf((request.getParameter("roleId") == null) ? "0" : request.getParameter("roleId"));
        if (groupId!=0&&roleId==0){
            List<UserVo> userVos = userService.selectByGroupId(groupId);
            if (userVos.isEmpty()){
                //Result error = result.error(400,"未查询到相关用户");
                return Result.error(50006,"查询用户失败");
            }
            // 错误码： 500xx
            //Result success=result.success(userVos);
            return Result.success(userVos);
        } else if (groupId==0&&roleId!=0) {
            List<UserVo> userVos=userService.selectByRoleId(roleId);
//            userVoArrayList.addAll(userVos);
            if (userVos.isEmpty()){
                //Result error = result.error(400, "未查询到相关用户");
                return Result.error(50006,"查询用户失败");
            }
            //Result success=result.success(userVos);
            return Result.success(userVos);
        }else if(groupId==0&&roleId==0){
            List<UserVo> userVos=userService.selectAll();
            if (userVos.isEmpty()){
                //Result error = result.error(400,"未查询到相关用户");
                return Result.error(50006,"查询用户失败");
            }
            //Result success=result.success(userVos);
            return Result.success(userVos);
        }
        //Result error = result.error(400,"未查询到相关用户");
        return Result.error(50006,"查询用户失败");
    }

    /**
     *  通过id查询用户信息 [id在路径后面]  " queryById/2  (id = 2)
     *  success
     * @param request
     * @param response
     * @return
     */
    private Result queryById(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        String userIdStr= split[split.length - 1];
        Long userId= 0L;
        boolean isInteger = true;
        for (int i = 0; i < userIdStr.length(); i++) {
            if (!Character.isDigit(userIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }

        if (isInteger) {
            userId = Long.parseLong(userIdStr);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        UserVo selected = userService.selectByUserId(userId);
        if(selected!=null) {
            //Result success=result.success(selected);
            return Result.success(selected);
        }
        //Result error = result.error(400,"未查询到相关用户");
        return Result.error(50006,"查询用户失败");
    }

    /**
     *  增加用户
     *  post 流读取
     *  success
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
            //Result success=result.success(null);
            return Result.success(null);
        }
        //Result error= result.error(400,"新增用户失败");
        return Result.error(50007,"添加用户失败");
    }

    /**
     *  修改用户信息
     *  post 流读取
     *  success
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
            //Result success = result.success(null);
            return Result.success(null);
        }
        //Result error= result.error(400,"修改用户信息失败");
        return Result.error(50005,"修改用户信息失败");
    }

    /**
     *  注销用户
     *  success
     *  param: 要删除用户的userId
     * @param request
     * @param response
     * @return
     */
    private Result logout(HttpServletRequest request,HttpServletResponse response){
        String userIdStr = request.getParameter("userId");
        Integer userId= 0;
        boolean isInteger = true;
        for (int i = 0; i < userIdStr.length(); i++) {
            if (!Character.isDigit(userIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }

        if (isInteger) {
            userId = Integer.parseInt(userIdStr);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        //Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer delete = userService.delete(userId);
        if (delete>0){
            //Result success = result.success(null);
            return Result.success(null);
        }
        //Result error = result.error(400,"未能删除成功");
        return Result.error(50008,"删除用户信息失败");
    }

    /**
     *  修改用户权限
     *  success
     *  param: UserRoleDto
     * @param request
     * @param response
     * @return
     */
    private Result updateRole(HttpServletRequest request, HttpServletResponse response){
        String roleIdStr = request.getParameter("roleId");
        Integer roleId=0;
        boolean isInteger = true;
        for (int i = 0; i < roleIdStr.length(); i++) {
            if (!Character.isDigit(roleIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }
        if (isInteger) {
            roleId = Integer.parseInt(roleIdStr);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        String userIdStr = request.getParameter("userId");
        Long userId= 0L;
        isInteger = true;
        for (int i = 0; i < userIdStr.length(); i++) {
            if (!Character.isDigit(userIdStr.charAt(i))) {
                isInteger = false;
                break;
            }
        }

        if (isInteger) {
            userId = Long.parseLong(userIdStr);
        } else {
            return Result.error(50009,"输入信息错误");
        }
        //Integer roleId = Integer.valueOf(request.getParameter("roleId"));
        //Long userId = Long.valueOf(request.getParameter("userId"));
        //Date date = new Date();
        userVo.setUserId(userId);
        //userVo.setGroupName();
        userVo.setRoleId(roleId);
        //userVo.setRoleName();
        Integer save = userService.updateUserRole(userVo);
        if (save>0){
           //Result success = result.success(null);
           return Result.success(null);
        }
        //Result error = result.error(400,"没有权限修改用户权限");
        return Result.error(50005,"修改用户信息失败");
    }

}
