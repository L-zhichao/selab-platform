package tyut.selab.userservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.impl.GroupServiceImpl;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: GroupController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:04
 * @version: 1.0
 */
@WebServlet(name="GroupController",value="/group/*")
public class GroupController extends HttpServlet {
    //private Result result=new Result(200,null);
    //private UserVo userVo = new UserVo();
    private GroupService groupService=new GroupServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if (methodName.equals("delete")) {
            try {
                Result delete = delete(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", delete.getCode());
                jsonObject.put("msg", delete.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (methodName.equals("queryAll")) {
            try {
                Result result = queryAllGroup(req, resp);
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
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        if (methodName.equals("save")) {
            try {
                Result save = save(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", save.getCode());
                jsonObject.put("msg", save.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if (methodName.equals("update")){
            try {
                Result update = update(req, resp);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", update.getCode());
                jsonObject.put("msg", update.getMsg());
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/html;charset=UTF-8");
                resp.getWriter().write(jsonObject.toJSONString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     *  新增小组
     *  succcess
     *  post 流读取
     *  param:GroupDto对象
     * @param req
     * @param resp
     * @return
     */
    private Result save(HttpServletRequest req, HttpServletResponse resp) {
        String jsonData = null;
        GroupDto groupDto = null;
        try {
            req.setCharacterEncoding("UTF-8");
            jsonData = req.getReader().lines().collect(Collectors.joining());
            groupDto = JSON.parseObject(jsonData, GroupDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Integer insert = groupService.insert(groupDto);
        if(insert>0){
            return Result.success(null);
        }
        return Result.error(50001, "添加小组失败");

    }

    /**
     *  修改小组信息
     *  post 流读取
     *  param: GroupVo对象
     *  success
     * @param req
     * @param resp
     * @return
     */
    private Result update(HttpServletRequest req, HttpServletResponse resp){
        String jsonData = null;
        GroupVo groupVo=null;
        try {
            req.setCharacterEncoding("UTF-8");
            jsonData = req.getReader().lines().collect(Collectors.joining());
            groupVo = JSON.parseObject(jsonData, GroupVo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Integer update = groupService.update(groupVo);
        if(update>0){
            return Result.success(null);
        }
        return Result.error(50002, "修改小组失败");
    }

    /**
     *  删除小组信息
     *  success
     *  param: groupId
     * @param req
     * @param resp
     * @return
     */
    private Result delete(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String groupIdStr = req.getParameter("groupId");
        Integer groupId = null;
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
        Integer delete = groupService.delete(groupId);
        if (delete>0){
            return Result.success(null);
        }
        return Result.error(50003, "删除小组失败");
    }

    /**
     * 查询所有小组信息
     *  param: cur size
     *  success
     * @param request
     * @param response
     * @return list<GroupVo>
     */
    private Result queryAllGroup(HttpServletRequest request,HttpServletResponse response){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Integer cur=0;
        String curStr=null;
        if (request.getParameter("cur")==null){
            cur=1;
        }else if (request.getParameter("cur")!=null){
            curStr = request.getParameter("cur");
            boolean isInteger = true;
            for (int i = 0; i < curStr.length(); i++) {
                if (!Character.isDigit(curStr.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (isInteger) {
                cur = Integer.parseInt(curStr);
            } else {
                return Result.error(50009,"输入信息错误");
            }
        }
        Integer szie=0;
        String szieStr=null;
        if (request.getParameter("szie")==null){
            szie=10;
        }else if (request.getParameter("szie")!=null){
            szieStr = request.getParameter("szie");
            boolean isInteger = true;
            for (int i = 0; i < szieStr.length(); i++) {
                if (!Character.isDigit(szieStr.charAt(i))) {
                    isInteger = false;
                    break;
                }
            }
            if (isInteger) {
                szie = Integer.parseInt(szieStr);
            } else {
                return Result.error(50009,"输入信息错误");
            }
        }
        //Integer cur = Integer.valueOf((request.getParameter("cur") == null) ? "1" : request.getParameter("cur"));
        //Integer szie = Integer.valueOf((request.getParameter("szie") == null) ? "5" : request.getParameter("szie"));

        List<GroupVo> groupVos = groupService.selectAllGroup(cur, szie);
        if (groupVos.isEmpty()){
            return Result.error(50004,"查询小组失败");
        }
        return Result.success(groupVos);
    }
}
