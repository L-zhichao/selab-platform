package tyut.selab.taskservice.controller;


import com.alibaba.fastjson.JSON;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.taskservice.common.HttpStatus;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.myutils.WebUtil;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.utils.Result;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet(name = "TaskController",urlPatterns = {"/task/*"})
public class TaskController extends HttpServlet {
    private TaskInfoService taskInfoService = new TaskServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //权限判断的,用于获取用户身份
//        String authorization = req.getHeader("Authorization");
//        System.out.println("Bear d 权限校验");
//        UserLocal user = SecurityUtil.getUser(authorization);

        // 响应的MIME类型和乱码问题
        resp.setContentType("application/json;charset=UTF-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        System.out.println(requestURI);

        String methodName = split[split.length - 1];
        // 通过反射获取要执行的方法
        Class clazz = this.getClass();
        Result result = null;
        try {
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 设置方法可以访问
            method.setAccessible(true);
            // 通过反射执行代码
            result = (Result) method.invoke(this, req, resp);
            WebUtil.writeJson(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
            result = Result.error(HttpStatus.NOT_FOUND, "未找到该接口");
            WebUtil.writeJson(resp, result);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应的MIME类型和乱码问题
        resp.setContentType("application/json;charset=UTF-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        try {
            //如果没有报错，就说明最后的一个参数是数字。
            Integer.parseInt(split[split.length - 1]);

            String methodName =split[split.length - 2];
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
            } catch (Exception e2) {
                e2.printStackTrace();
                result = Result.error(HttpStatus.NOT_FOUND,"未找到该接口");
                WebUtil.writeJson(resp,result);
            }
        }catch(Exception e1) {
            //如果最后一个参数不是数字的话执行下面的方法
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
            } catch (Exception e2) {
                e2.printStackTrace();
                result = Result.error(HttpStatus.NOT_FOUND,"未找到该接口");
                WebUtil.writeJson(resp,result);
            }
        }

    }

    /**
     *  增加任务
     * param TaskInfoDto taskInfoDto
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
        TaskInfoDto taskInfoDto = WebUtil.readJson(request, TaskInfoDto.class);

        //判断用户身份，不同身份发布任务的范围不一样，权限不够就返回error
        //
        //
        //
        //
        //相同的用户发布的任务最大个数应该有限制
        //
        //
        //
        //
        //相关的一些接口功能上是否有线程安全问题？
        //
        //
        //
        //保存和修改任务的时候，截至时间是不是应该比当前时间多
        //
        //
        //

        int i = taskInfoService.save(taskInfoDto);
        //添加任务的时候标题和内容不能重复
        if (i == 0 ){
            return Result.error(HttpStatus.CONFLICT,"任务已存在");
        }else{
            return Result.success(null);
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

           //判断前端查询的页面是否存在，如果不存在，返回空集合
           int beginIndex = (cur - 1) * size;
           int endIndex = cur * size;
           if (beginIndex > taskInfoVos.size() - 1){
               //该页面没有数据
           }else if(endIndex > taskInfoVos.size()){
               //该页面的数据不够size个
               taskInfoVoPage = taskInfoVos.subList(beginIndex,taskInfoVos.size());
           }else{
               //该页面的数据满足size个
               taskInfoVoPage = taskInfoVos.subList(beginIndex,endIndex);
           }
       }else{
           //查询本人发布的任务信息
           List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoBypublish(publisherName);
           //判断前端查询的页面是否存在，如果不存在，返回空集合
           int beginIndex = (cur - 1) * size;
           int endIndex = cur * size;
           if (beginIndex > taskInfoVos.size() - 1){
               //该页面没有数据
           }else if(endIndex > taskInfoVos.size()){
               //该页面的数据不够size个
               taskInfoVoPage = taskInfoVos.subList(beginIndex,taskInfoVos.size());
           }else{
               //该页面的数据满足size个
               taskInfoVoPage = taskInfoVos.subList(beginIndex,endIndex);
           }
       }
       //查询后进行分页
       return Result.success(taskInfoVoPage);
   }

    /**
     *  修改任务信息
     * @param request
     * @param response
     * @return
     */
  private Result update(HttpServletRequest request,HttpServletResponse response){
      //如果不是任务发布者或者是超级管理员的话，权限不够禁止修改
      //
      //
      //
      //


      //获取前端传入的taskid
      String requestURI = request.getRequestURI();
      String[] split = requestURI.split("/");
      int taskId = Integer.parseInt(split[split.length - 1]);


      TaskInfoDto taskInfoDto = WebUtil.readJson(request, TaskInfoDto.class);

      Integer row = taskInfoService.update(taskInfoDto, taskId);
      if(row == 0){
          return Result.error(HttpStatus.NOT_FOUND,"任务不存在");
      }else if(row == 1){
          //如果修改的标题和内容和已有的，没有被数据库删除的任务冲突，则报错
          return Result.error(HttpStatus.CONFLICT,"修改的任务和现有的任务冲突");
      }
      else{
          return Result.success(null);
      }
  }

    /**
     * 通过taskId查询任务信息
     *
     * @param request
     * @param response
     * @return
     */
    private Result<TaskInfoVo> queryById(HttpServletRequest request, HttpServletResponse response) {
        //权限问题，超级管理员能查询所有的任务id，管理员只能查看自己发布的任务，用户只能查看自己接收的任务
        //
        //


        int taskId = Integer.parseInt(request.getParameter("taskId"));
        TaskInfoVo taskInfoVo = taskInfoService.queryById(taskId);
        if (taskInfoVo != null) {
            return Result.success(taskInfoVo);
        }

        return Result.error(HttpStatus.NOT_FOUND, "该任务不存在");
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



    /**
     * 进行身份认定，返回用户的身份角色id
     * @param request
     * @param response
     * @return
     */
    private int roleId(HttpServletRequest request,HttpServletResponse response){
        return 0;
    }
}
