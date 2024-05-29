package tyut.selab.taskservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.taskservice.common.HttpStatus;
import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.domain.TaskReport;
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
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
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
    TaskInfoService taskInfoService=new TaskServiceImpl();
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
    private Result report(HttpServletRequest request,HttpServletResponse response){

        TaskReportDto taskReportDto =WebUtil.readJson(request,TaskReportDto.class);
        Integer save = taskReportService.save(taskReportDto);
        if(save!=null){
            return Result.success(null);//待修改
        }else{
            return Result.error(HttpStatus.ERROR,"汇报失败");//?????
        }

    }

    /**
     *  查询当前汇报数量
     *  param: taskId
     * @param request
     * @return
     */
    private Result queryCount(HttpServletRequest request,HttpServletResponse response) throws SQLException {

        TaskReport taskReport = new TaskReport();

       //判断任务是否存在
       Integer taskId = Integer.valueOf(request.getParameter("taskId"));
        List<TaskReportVo> taskReportVos = taskReportService.queryAllTask(taskId);
        if(taskReportVos==null){
            return  Result.error(HttpStatus.NOT_FOUND, "该任务不存在");
        }

        //获取用户id
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();

        //权限不够-->管理员查询非自己发布的任务 && 普通用户
        if(!(taskReport.getUserId().equals(roleId)) && roleId==3){
            return Result.error(HttpStatus.UNAUTHORIZED,"权限不够,禁止查询");
        }else {
            //查询
            Integer count = taskReportService.queryTaskReportCount(taskId);
            return Result.success(count,"操作成功");
        }

    }



    /**
     *  通过taskId查询本人汇报记录 【userid后端获取】
     *  param: taskId
     * @param request
     * @param response
     * @return TaskReportDto
     */
    private Result queryMyReport(HttpServletRequest request, HttpServletResponse response){
        List<TaskReportVo> taskReportVos = new ArrayList<TaskReportVo>();

        //获取用户id
        UserLocal userMessage = getUserMessage(request, response);
        Integer userId = userMessage.getUserId();

        //获取请求参数
        Integer taskId = Integer.valueOf(request.getParameter("taskId"));
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));

      //  taskReportVos = taskReportService.queryByUserIdAndTaskId(taskId, userId);

        if(taskReportVos!=null){
            return Result.success(null);//嗲修改
        }else {
            return Result.error(HttpStatus.ERROR,"查询失败");//????
        }

    }

    /**
     *  通过id查询任务的所有汇报记录
     *  param: taskId
     * @param request
     * @param response
     * @return List<TaskInfoVo>
     */
    private Result queryAllResport(HttpServletRequest request,HttpServletResponse response){
        List<TaskReportVo> taskReportVos = new ArrayList<TaskReportVo>();
        Integer taskid = null;
        int cur=0;
        int size=0;
        //权限判断
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        //普通用户无法查看
        if (roleId==3){
            return Result.error(HttpStatus.UNAUTHORIZED,"普通用户不能查看汇报记录");
        }else if (roleId==2){
            //普通管理员
            //判断参数是否为空
            if (request.getParameter("cur")!=null&&request.getParameter("size")!=null){
                 cur = Integer.parseInt(request.getParameter("cur"));
                size = Integer.parseInt(request.getParameter("size"));
                if (cur<0||size<0){
                    return Result.error(HttpStatus.UNSUPPORTED_TYPE,"参数非法");
                }
            }
            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            //不输入id情况
            if (taskid==null){
                //获取用户发布的所有任务
                List<TaskReportVo> successT=new ArrayList<>();
                String userName = userMessage.getUserName();
                List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoBypublish(userName);
                for (TaskInfoVo taskInfoVo:taskInfoVos){
                    try {
                        taskReportVos = taskReportService.queryAllTask(taskInfoVo.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (taskReportVos!=null) {
                        if (cur>0&&size>0){
                            int beginIndex = (cur - 1) * size;
                            int endIndex = cur * size - 1;
                            List<TaskReportVo> taskInfoVoPage;
                            if (beginIndex > taskReportVos.size() - 1){
                                taskInfoVoPage = null;
//                            successT.addAll(taskInfoVoPage);
                            }else if(endIndex > taskReportVos.size() - 1){
                                taskInfoVoPage = taskReportVos.subList(beginIndex,taskReportVos.size());
                                successT.addAll(taskInfoVoPage);
                            }else{
                                taskInfoVoPage = taskReportVos.subList(beginIndex,endIndex+1);
                                successT.addAll(taskInfoVoPage);
                            }
                        }else {
                            successT.addAll(taskReportVos);
                        }
                    }
                }
                if (successT==null){
                    return Result.error(HttpStatus.NO_CONTENT,"所有任务暂时还没有汇报记录");
                }else {
                    WebUtil.writeJson(response,Result.success(successT));
                    return Result.success(successT);
                }

            }
            //id是否输入合法
            Integer userId = userMessage.getUserId();
            TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
            if (userId==taskInfo.getPublisherId()){
                try {
                    taskReportVos = taskReportService.queryAllTask(taskid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (taskReportVos==null){
                    return Result.error(HttpStatus.NO_CONTENT,"该任务暂时还没有汇报记录");
                }else {
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur>0&&size>0){
                        int beginIndex = (cur - 1) * size;
                        int endIndex = cur * size - 1;
                        if (beginIndex > taskReportVos.size() - 1){
                            taskInfoVoPage = null;
                        }else if(endIndex > taskReportVos.size() - 1){
                            taskInfoVoPage = taskReportVos.subList(beginIndex,taskReportVos.size());
                        }else{
                            taskInfoVoPage = taskReportVos.subList(beginIndex,endIndex+1);
                        }
                    }else {
                        taskInfoVoPage=taskReportVos;
                    }
                    WebUtil.writeJson(response,Result.success(taskInfoVoPage));
                    return Result.success(taskInfoVoPage);
                }
            }else {
                return Result.error(HttpStatus.UNAUTHORIZED,"没有权限查看他人任务的汇报记录");
            }
        }else {
            //判断参数是否为空
            if (request.getParameter("cur")!=null&&request.getParameter("size")!=null){
                cur = Integer.parseInt(request.getParameter("cur"));
                size = Integer.parseInt(request.getParameter("size"));
                if (cur<0||size<0){
                    return Result.error(HttpStatus.UNSUPPORTED_TYPE,"参数非法");
                }
            }
            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            if (taskid!=null){
                try {
                    taskReportVos = taskReportService.queryAllTask(taskid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (taskReportVos==null){
                    return Result.error(HttpStatus.NO_CONTENT,"该任务暂时还没有汇报记录");
                }else {
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur>0&&size>0){
                        int beginIndex = (cur - 1) * size;
                        int endIndex = cur * size - 1;
                        if (beginIndex > taskReportVos.size() - 1){
                            taskInfoVoPage = null;
                        }else if(endIndex > taskReportVos.size() - 1){
                            taskInfoVoPage = taskReportVos.subList(beginIndex,taskReportVos.size());
                        }else{
                            taskInfoVoPage = taskReportVos.subList(beginIndex,endIndex+1);
                        }
                    }else {
                        taskInfoVoPage=taskReportVos;
                    }
                    WebUtil.writeJson(response,Result.success(taskInfoVoPage));
                    return Result.success(taskInfoVoPage);
                }
            }else {
                BaseDao baseDao = new BaseDao();
                String str1 = """
select DISTINCT task_id from task_report
""";
                List<Integer>taskids=new ArrayList<>();
                taskids=baseDao.baseQuery(Integer.class, str1);
                if (taskids==null){
                    return Result.error(HttpStatus.NO_CONTENT,"暂无汇报信息");
                }else {
                    List<TaskReportVo> SuccessTaskReportVos = new ArrayList<>();
                    for (Integer id:taskids){
                        try {
                            taskReportVos = taskReportService.queryAllTask(id);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        SuccessTaskReportVos.addAll(taskReportVos);
                    }
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur>0&&size>0){
                        int beginIndex = (cur - 1) * size;
                        int endIndex = cur * size - 1;
                        if (beginIndex > SuccessTaskReportVos.size() - 1){
                            taskInfoVoPage = null;
                        }else if(endIndex > SuccessTaskReportVos.size() - 1){
                            taskInfoVoPage = SuccessTaskReportVos.subList(beginIndex,SuccessTaskReportVos.size());
                        }else{
                            taskInfoVoPage = SuccessTaskReportVos.subList(beginIndex,endIndex+1);
                        }
                    }else {
                        taskInfoVoPage=SuccessTaskReportVos;
                    }
                    WebUtil.writeJson(response,Result.success(taskInfoVoPage));
                    return Result.success(taskInfoVoPage);
                }
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
        }else if (roleId==2){
            //判断是否有权限删除
            //根据reportid查出该任务的发布者
            Integer userId = userMessage.getUserId();
            Integer publisherId=taskReportService.queryuseridByreportid(reportid);
            if (userId==publisherId){
                //执行删除任务
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
            }else {
                return Result.error(HttpStatus.UNAUTHORIZED,"没有权限删除他人任务的汇报记录");
            }
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
    private Result queryAllNeedReportUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<NeedReportUser> needReportUsers = new ArrayList<>();
        //权限判断
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        Integer taskid =null;
        int cur=0;
        int size=0;

        if (roleId == 3 ){
            return Result.error(HttpStatus.UNAUTHORIZED,"普通用户不能查看所有需要汇报的用户");
        }else if (roleId==2){
            //管理员只能查看自己发布的需要汇报的用户信息
            //读取参数 cur size
            //taksid 输入非法： 不是自己发布的任务的id？？？？ 待处理
            if (request.getParameter("cur")!=null&&request.getParameter("size")!=null){
                cur = Integer.parseInt(request.getParameter("cur"));
                size = Integer.parseInt(request.getParameter("size"));
                if (cur<0||size<0){
                    return Result.error(HttpStatus.UNSUPPORTED_TYPE,"参数非法");
                }
            }

            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            //无id，获取自己发布的所有任务
            if (taskid==null){
                //获取自己的所有任务
                String userName = userMessage.getUserName();
                List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoBypublish(userName);
                List<NeedReportUser> successN=new ArrayList<>();
                for (TaskInfoVo taskInfoVo:taskInfoVos){
                     needReportUsers = taskReportService.queryAllUserForReport(taskInfoVo.getId());
                    if (needReportUsers!=null) {
                        if (cur>0&&size>0){
                            int beginIndex = (cur - 1) * size;
                            int endIndex = cur * size - 1;
                            List<NeedReportUser> Page;
                            if (beginIndex > needReportUsers.size() - 1){
                                Page = null;
//                            successN.addAll(Page);
                            }else if(endIndex > needReportUsers.size() - 1){
                                Page = needReportUsers.subList(beginIndex,needReportUsers.size());
                                successN.addAll(Page);
                            }else{
                                Page = needReportUsers.subList(beginIndex,endIndex+1);
                                successN.addAll(Page);
                            }
                        }else {
                            successN.addAll(needReportUsers);
                        }
                    }
                }
                if (successN==null){
                    return Result.error(HttpStatus.NO_CONTENT,"所有任务暂时还没有需要汇报的用户");
                }else {
                    WebUtil.writeJson(response,Result.success(successN));
                    return Result.success(successN);
                }
            }
            //taksid 输入非法： 不是自己发布的任务的id
            Integer userId = userMessage.getUserId();
            TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
            if (userId!=taskInfo.getPublisherId()){
                return Result.error(HttpStatus.UNAUTHORIZED,"没有权限查看他人任务的汇报用户");
            }else {
                //taskid->groupid->userid
                needReportUsers = taskReportService.queryAllUserForReport(taskid);
                if (needReportUsers==null){
                    return Result.error(HttpStatus.NO_CONTENT,"该任务没有汇报的用户");
                }else {
                    List<NeedReportUser> Page=new ArrayList<>();
                    if (cur>0&&size>0){
                        int beginIndex = (cur - 1) * size;
                        int endIndex = cur * size - 1;
                        if (beginIndex > needReportUsers.size() - 1){
                            Page = null;
                        }else if(endIndex > needReportUsers.size() - 1){
                            Page = needReportUsers.subList(beginIndex,needReportUsers.size());
                        }else{
                            Page = needReportUsers.subList(beginIndex,endIndex+1);
                        }
                    }else {
                        Page.addAll(needReportUsers);
                    }
                    WebUtil.writeJson(response,Result.success(Page));
                    return Result.success(Page);
                }
            }
        }else {
            //超级管理员
            if (request.getParameter("cur")!=null&&request.getParameter("size")!=null){
                cur = Integer.parseInt(request.getParameter("cur"));
                size = Integer.parseInt(request.getParameter("size"));
                if (cur<0||size<0){
                    return Result.error(HttpStatus.UNSUPPORTED_TYPE,"参数非法");
                }
            }
            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            //超级管理员能查看所有发布的需要汇报的用户信息
           //超级管理员也可以输入id
            if (taskid!=null){
                //查询特定任务的汇报用户
                needReportUsers = taskReportService.queryAllUserForReport(taskid);
                if (needReportUsers==null){
                    return Result.error(HttpStatus.NO_CONTENT,"该任务没有汇报的用户");
                }else {
                    List<NeedReportUser> Page=new ArrayList<>();
                    if (cur>0&&size>0){
                        int beginIndex = (cur - 1) * size;
                        int endIndex = cur * size - 1;
                        if (beginIndex > needReportUsers.size() - 1){
                            Page = null;
                        }else if(endIndex > needReportUsers.size() - 1){
                            Page = needReportUsers.subList(beginIndex,needReportUsers.size());
                        }else{
                            Page = needReportUsers.subList(beginIndex,endIndex+1);
                        }
                    }else {
                        Page.addAll(needReportUsers);
                    }
                    WebUtil.writeJson(response,Result.success(Page));
                    return Result.success(Page);
                }
            }else {
                //查询所有任务的汇报用户
                //获取所有taskid
                BaseDao baseDao = new BaseDao();
                String str1 = """
                select DISTINCT id from task_info
                              """;
                List<Integer>taskids=new ArrayList<>();
                taskids=baseDao.baseQuery(Integer.class, str1);
                if (taskids==null){
                    return Result.error(HttpStatus.NO_CONTENT,"暂无任务发布,没有可以汇报的用户");
                }else {
                    List<NeedReportUser> successN=new ArrayList<>();
                    for (Integer i:taskids){
                         needReportUsers = taskReportService.queryAllUserForReport(i);
                        if (needReportUsers!=null) {
                            if (cur>0&&size>0){
                                int beginIndex = (cur - 1) * size;
                                int endIndex = cur * size - 1;
                                List<NeedReportUser> Page;
                                if (beginIndex > needReportUsers.size() - 1){
                                    Page = null;
//                                successN.addAll(Page);
                                }else if(endIndex > needReportUsers.size() - 1){
                                    Page = needReportUsers.subList(beginIndex,needReportUsers.size());
                                    successN.addAll(Page);
                                }else{
                                    Page = needReportUsers.subList(beginIndex,endIndex+1);
                                    successN.addAll(Page);
                                }
                            }else {
                                successN.addAll(needReportUsers);
                            }
                        }
                    }
                    if (successN==null){
                        return Result.error(HttpStatus.NO_CONTENT,"所有任务暂时还没有需要汇报的用户");
                    }else {
                        WebUtil.writeJson(response,Result.success(successN));
                        return Result.success(successN);
                    }
                }
            }
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

