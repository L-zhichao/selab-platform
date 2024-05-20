package tyut.selab.taskservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.taskservice.common.HttpStatus;
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
        //1.0读取参数
        Integer taskid = Integer.parseInt(request.getParameter("taskid"));
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        //权限处理 不会

        //3.0调用service方法
        if (taskid!=null){
            //通过id查询任务汇报记录
            //异常未处理
            try {
                taskReportVos = taskReportService.queryAllTask(taskid);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            //无id咋搞?
        }
//返回josn数据
        if (taskReportVos==null){
            //返回错误 ????
            return Result.error(204,"操作已经执行成功，但是没有返回数据");

        }else {
            //成功返回
            WebUtil.writeJson(response,Result.success(taskReportVos));
            return Result.success(taskReportVos);
        }
    }

    /**
     *  删除汇报记录[管理员]
     *  param: reportId
     * @param request
     * @param response
     * @return
     */
    private Result delete(HttpServletRequest request,HttpServletResponse response){
        //权限判断:只有管理员才能进行删除操作

        //读取参数
        Integer reportid = Integer.parseInt(request.getParameter("reportid"));
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));

        return null;}

    /**
     *  查询所有需要汇报的用户
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskReportVo>
     */
    private Result queryAllNeedReportUser(HttpServletRequest request,HttpServletResponse response){
        return null;}
}

