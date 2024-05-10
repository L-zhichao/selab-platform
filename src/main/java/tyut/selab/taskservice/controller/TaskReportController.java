package tyut.selab.taskservice.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: TaskReportController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 16:32
 * @version: 1.0
 */
public class TaskReportController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     *  汇报任务
     *  param: TaskReportDto 对象
     * @param request
     * @param response
     * @return
     */
    private Result<> report(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  查询当前汇报数量
     *  param: taskId
     * @param request
     * @param response
     * @return
     */
    private Result<> queryCount(HttpServletRequest request,HttpServletResponse response){return null;}



    /**
     *  通过taskId查询本人汇报记录 【userid后端获取】
     *  param: taskId
     * @param request
     * @param response
     * @return TaskReportDto
     */
    private Result<> queryMyReport(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  通过id查询任务的所有汇报记录
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskInfoVo>
     */
    private Result<> queryAllResport(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  删除汇报记录[管理员]
     *  param: reportId
     * @param request
     * @param response
     * @return
     */
    private Result<> delete(HttpServletRequest request,HttpServletResponse response){return null;}

    /**
     *  查询所有需要汇报的用户
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskReportVo>
     */
    private Result<> queryAllNeedReportUser(HttpServletRequest request,HttpServletResponse response){return null;}



}

