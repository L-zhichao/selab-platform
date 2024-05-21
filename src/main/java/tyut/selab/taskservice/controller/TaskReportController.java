package tyut.selab.taskservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.taskservice.common.HttpStatus;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.myutils.WebUtil;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.service.impl.TaskReportServiceImpl;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.utils.Result;


import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @className: TaskReportController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 16:32
 * @version: 1.0
 */
@WebServlet("/task/report/*")
public class TaskReportController extends HttpServlet {

    private Result resultMaker = new Result(HttpStatus.SUCCESS,null);
    private TaskReportService taskReportService =new TaskReportServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应的MIME类型和乱码问题
        resp.setContentType("application/json;charset=UTF-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName =split[split.length-1];
        // 通过反射获取要执行的方法
        Class clazz = this.getClass();
        Result result = null;
        try {
            Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            // 设置方法可以访问
            method.setAccessible(true);
            // 通过反射执行代码
            result = (Result) method.invoke(this,req,resp);
            WebUtil.writeJson(resp,result);
        } catch (Exception e) {
            e.printStackTrace();
            result = resultMaker.error(HttpStatus.NOT_FOUND,"未找到该接口");
            WebUtil.writeJson(resp,result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应的MIME类型和乱码问题
        resp.setContentType("application/json;charset=UTF-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName =split[split.length-1];
        // 通过反射获取要执行的方法
        Class clazz = this.getClass();
        Result result = null;
        try {
            Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            // 设置方法可以访问
            method.setAccessible(true);
            // 通过反射执行代码
            result = (Result) method.invoke(this,req,resp);
            WebUtil.writeJson(resp,result);
        } catch (Exception e) {
            e.printStackTrace();
            result = resultMaker.error(HttpStatus.NOT_FOUND,"未找到该接口");
            WebUtil.writeJson(resp,result);
        }
    }

    /**
     *  汇报任务
     *  param: TaskReportDto 对象
     * @param request
     * @param response
     * @return
     */
    private Result report(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  查询当前汇报数量
     *  param: taskId
     * @param request
     * @param response
     * @return
     */
    private Result queryCount(HttpServletRequest request,HttpServletResponse response){return null;}



    /**
     *  通过taskId查询本人汇报记录 【userid后端获取】
     *  param: taskId
     * @param request
     * @param response
     * @return TaskReportDto
     */
    private Result queryMyReport(HttpServletRequest request, HttpServletResponse response){return null;}

    /**
     *  通过id查询任务的所有汇报记录
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskInfoVo>
     */
    private Result queryAllResport(HttpServletRequest request,HttpServletResponse response){
       // TaskReportVo taskReportVo = new TaskReportVo();
        List<TaskReportVo> taskReportVos = new ArrayList<TaskReportVo>();
        Integer taskid = null;
//        int cur = 0;
//        int size = 0;
        //权限处理 不会 待细化?????
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        if (roleId==3){
            return Result.error(HttpStatus.UNAUTHORIZED,"普通用户不能查看汇报记录");
        }else if (roleId==2){
            //管理员只能查看自己的
            //1.0读取参数 cur size 未处理
            taskid = Integer.parseInt(request.getParameter("taskid"));
            int cur = Integer.parseInt(request.getParameter("cur"));
            int size = Integer.parseInt(request.getParameter("size"));
            try {
                taskReportVos = taskReportService.queryAllTask(taskid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //返回josn数据
            if (taskReportVos==null){
                //返回错误 ????
                return Result.error(HttpStatus.NO_CONTENT,"操作已经执行成功，但是没有返回数据");

            }else {
                //成功返回
                WebUtil.writeJson(response,Result.success(taskReportVos));
                return Result.success(taskReportVos);
            }
        }else {//超级管理员可以查看所有的
            taskid = Integer.parseInt(request.getParameter("taskid"));
          int  cur = Integer.parseInt(request.getParameter("cur"));
           int size = Integer.parseInt(request.getParameter("size"));
            if (taskid!=null){
                //通过id查询任务汇报记录
                //异常未处理
                try {
                    taskReportVos = taskReportService.queryAllTask(taskid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //返回josn数据
                if (taskReportVos==null){
                    //返回错误 ????
                    return Result.error(HttpStatus.NO_CONTENT,"操作已经执行成功，但是没有返回数据");

                }else {
                    //成功返回
                    WebUtil.writeJson(response,Result.success(taskReportVos));
                    return Result.success(taskReportVos);
                }
            }else {
                //无id咋搞?
                //获取所有任务id

                return null;
            }
        }
    }

    /**
     *  删除汇报记录[管理员]
     *  param: reportId
     * @param request
     * @param response
     * @return
     * 直接将汇报记录删除了，并不是逻辑删除(表中没用状态列,所以直接删了)
     */
    private Result delete(HttpServletRequest request,HttpServletResponse response){
        TaskReportDaoImpl taskReportDao=new TaskReportDaoImpl();
        //权限判断:只有管理员才能进行删除操作
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        //读取参数
        Integer reportid = Integer.parseInt(request.getParameter("reportid"));
        //权限判断
        if (roleId == 3 ){
            return Result.error(HttpStatus.UNAUTHORIZED,"普通用户不能删除汇报记录");
        }else {
            //直接调用dao方法
            Integer i = taskReportDao.deleteByReportId(reportid);
            //检查执行结果
            if (i==1){
                //成功找到任务，并且完成删除操作
                WebUtil.writeJson(response,resultMaker);
                return resultMaker;
            }else {
                //未找到
                return Result.error(HttpStatus.NOT_FOUND,"未找到该汇报记录");
            }
        }
        }

    /**
     *  查询所有需要汇报的用户
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskReportVo>
     */
    private Result queryAllNeedReportUser(HttpServletRequest request,HttpServletResponse response){
        List<NeedReportUser> needReportUsers = new ArrayList<>();
        //权限判断 未完成
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        if (roleId == 3 ){
            return Result.error(HttpStatus.UNAUTHORIZED,"普通用户不能查看所有需要汇报的用户");
        }else if (roleId==2){
            //管理员只能查看自己发布的需要汇报的用户信息
            //读取参数 cur size 未处理
            Integer taskid = Integer.parseInt(request.getParameter("taskid"));
            int cur = Integer.parseInt(request.getParameter("cur"));
            int size = Integer.parseInt(request.getParameter("size"));
            //读取xml数据


            return Result.success(needReportUsers);
        }else {
            //超级管理员能查看所有发布的需要汇报的用户信息
           //超级管理员也可以输入id

            //获取所有任务id

            return Result.success(needReportUsers);
        }



       }
    /**
     * 非业务接口方法
     * 进行身份认定，返回用户的身份信息
     * @param request
     * @param response
     * @return 返回一个UserLocal对象
     *  user 中的 roleId   1 标识超级管理员，返回 2 标识管理员，返回 3 表示普通用户
     */
    private UserLocal getUserMessage(HttpServletRequest request,HttpServletResponse response){
        UserLocal user = SecurityUtil.getUser();
        return user;
    }
}

