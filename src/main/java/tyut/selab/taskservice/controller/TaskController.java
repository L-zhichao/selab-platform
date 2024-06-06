package tyut.selab.taskservice.controller;


import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.utils.Result;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TaskController",urlPatterns = {"/"})
public class TaskController extends HttpServlet {
    private TaskInfoService taskInfoService = new TaskServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     *  增加任务
     * param TaskInfoDto taskInfoDto
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
     return null;
    }

    /**
     *  查询所有任务 【cur size为必需参数】（参数不为空是则查询指定用户发布任务，为空则查询所有任务）
     *  param: cur size publish 任务发布者名称userName
     * @param request
     * @param response
     * @return
     */
    private Result queryAll(HttpServletRequest request,HttpServletResponse response){
      return null;
    }

    /**
     *  用户查询本人的任务信息
     *  param:null 【userId 通过loginservice模块SecurityUtil方法获取】
     * @param request
     * @param response
     * @return
     */
   private Result queryMyTask(HttpServletRequest request,HttpServletResponse response){
     return null;
   }

    /**
     *  修改任务信息
     * @param request
     * @param response
     * @return
     */
  private Result update(HttpServletRequest request,HttpServletResponse response){
     return null;
  }

    /**
     * 通过taskId查询任务信息
     * @param request
     * @param response
     * @return
     */
   private Result<TaskInfoVo> queryById(HttpServletRequest request, HttpServletResponse response){
    return null;
   }


    /**  删除任务
     *   param: taskId
     * @param request
     * @param response
     * @return
     */
    private Result delete(HttpServletRequest request,HttpServletResponse response){return null;}


}
