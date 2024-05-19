package tyut.selab.taskservice.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.taskservice.common.HttpStatus;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.myutils.WebUtil;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoForUser;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.utils.Result;


import java.io.IOException;
import java.lang.reflect.Method;
import java.net.CacheRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TaskController",urlPatterns = {"/task/*"})
public class TaskController extends HttpServlet {
    private TaskInfoService taskInfoService = new TaskServiceImpl();
    private Result resultMaker = new Result(HttpStatus.SUCCESS,null);

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
     *  增加任务
     * param TaskInfoDto taskInfoDto
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
        TaskInfoDto taskInfoDto = WebUtil.readJson(request, TaskInfoDto.class);
        int i = taskInfoService.save(taskInfoDto);
        if (i == 0 ){
            return resultMaker.error(HttpStatus.CONFLICT,"任务已存在");
        }else{
            return resultMaker.success(null);
        }
    }

    /**
     *  查询本人接收的所有任务 【cur size为必需参数】
     *  param: cur size publish 任务发布者名称userName
     *  param:null 【userId 通过loginservice模块SecurityUtil方法获取】
     * @param request
     * @param response
     * @return
     */
    private Result queryAll(HttpServletRequest request,HttpServletResponse response){
      return null;
    }

    /**
     *  用户查询本人发布的任务信息（参数不为空是则查询指定用户发布的任务，为空则查询所有任务）
     *  param: cur(页数） size（每页的大小） publish 任务发布者名称userName
     * @param request
     * @param response
     * @return
     */
   private Result queryMyTask(HttpServletRequest request,HttpServletResponse response){
       String publisherName = request.getParameter("publisherName");
       int cur = Integer.parseInt(request.getParameter("cur"));
       int size = Integer.parseInt(request.getParameter("size"));
       List<TaskInfoVo> taskInfoVoPage = null;
       if (publisherName == null){
           //查询所有
           List<TaskInfoVo> taskInfoVos = taskInfoService.queryAllTask();
           taskInfoVoPage = taskInfoVos.subList((cur - 1) * size , cur * size );
       }else{
           //查询本人发布的任务信息
           List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoBypublish(publisherName);
           taskInfoVoPage = taskInfoVos.subList((cur - 1) * size , cur * size );
       }
       //查询后进行分页
       return resultMaker.success(taskInfoVoPage);
   }

    /**
     *  修改任务信息
     * @param request
     * @param response
     * @return
     */
  private Result update(HttpServletRequest request,HttpServletResponse response){
      //如果不是任务发布者或者是超级管理员的话，权限不够禁止修改


      TaskInfoDto taskInfoDto = WebUtil.readJson(request, TaskInfoDto.class);
      int taskId = Integer.parseInt(request.getParameter("taskId"));
      Integer row = taskInfoService.update(taskInfoDto, taskId);
      if(row == 0){
          return resultMaker.error(HttpStatus.NOT_FOUND,"任务不存在");
      }else {
          return resultMaker.success(null);
      }
  }

    /**
     * 通过taskId查询任务信息
     * @param request
     * @param response
     * @return
     */
   private Result<TaskInfoVo> queryById(HttpServletRequest request, HttpServletResponse response){
       int taskId = Integer.parseInt(request.getParameter("taskId"));
       TaskInfoVo taskInfoVo = taskInfoService.queryById(taskId);
       if (taskInfoVo != null){
           return resultMaker.success(taskInfoVo);
       }

       return resultMaker.error(HttpStatus.NOT_FOUND,"该任务不存在");
   }


    /**  删除任务
     *   param: taskId
     * @param request
     * @param response
     * @return
     */
    private Result delete(HttpServletRequest request,HttpServletResponse response){
        //从请求中获取taskId
       String taskId= request.getParameter("taskId");
        if (taskId == null || taskId.isEmpty()) {
            // 如果 taskId 为空或不存在，返回错误结果
            return resultMaker.error(resultMaker.getCode(), resultMaker.getMsg());
        }
        else return resultMaker;
    }



}
