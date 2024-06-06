package tyut.selab.taskservice.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.taskservice.common.HttpStatus;
import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.dao.impl.TaskGroupDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.ReportDto;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.myutils.Task;
import tyut.selab.taskservice.myutils.WebUtil;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.service.impl.TaskReportServiceImpl;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoForUser;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.utils.Page;
import tyut.selab.utils.Result;


import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    TaskInfoService taskService=new TaskServiceImpl();
    TaskInfoService taskInfoService=new TaskServiceImpl();
    TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
    TaskGroupDaoImpl taskGroupDao = new TaskGroupDaoImpl();
    TaskReportDaoImpl taskReportDao=new TaskReportDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        findMethod(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        findMethod(req,resp);
    }

    /**
     *  汇报任务
     *  param: TaskReportDto 对象
     * @param request
     * @param response
     * @return
     */
    private Result report(HttpServletRequest request,HttpServletResponse response){

       ReportDto reportDto;
        //将请求体中的JSON数据转换为ReportDto-->TaskReportDto对象
       reportDto=WebUtil.readJson(request, ReportDto.class);
       TaskReportDto taskReportDto = reportDto.taskReportDto;

        UserLocal userMessage = getUserMessage(request, response);
        Integer userId = userMessage.getUserId();

        //将userId传入service层
        taskReportService.setUserId(userId);

        Integer save = taskReportService.save(taskReportDto);
        if(save!=null){
            if(save == -1){
                return Result.error(HttpStatus.PermissionNotAllowed,"非本人任务，无法汇报");
            }else {
                return Result.success(HttpStatus.NO_CONTENT,"汇报成功");
            }
        }
        return Result.error(HttpStatus.CONFLICT,"存在相同汇报信息");
    }

    /**
     *  查询当前汇报数量
     *  param: taskId
     * @param request
     * @return
     */
    private Result queryCount(HttpServletRequest request,HttpServletResponse response) throws SQLException {

     //获取请求参数
        String taskIdStr = request.getParameter("taskId");
        if (taskIdStr == null || taskIdStr.isEmpty()) {
            return Result.error(HttpStatus.IncomingDataError, "taskId不能为空");
        }

        Integer taskId = null;
        try {
            taskId = Integer.valueOf(taskIdStr);
        } catch (NumberFormatException e) {
            return Result.error(HttpStatus.IncomingDataError, "taskId必须为整数");
        }

        //判断任务是否存在
        TaskInfoVo taskInfoVo = taskService.queryById(taskId);
        if(taskInfoVo==null){
            return  Result.error(HttpStatus.NoDataFromDatabase, "该任务不存在");
        }

        //发布者id
        TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskId);
        Integer publisherId = taskInfo.getPublisherId();

        //角色id
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();

        //权限
        if ( roleId == 3) {
            return Result.error(HttpStatus.PermissionNotAllowed, "权限不足，无法查询：普通用户");
        } else if (roleId==2) {

            Integer userId=userMessage.getUserId();
            if(!Objects.equals(publisherId,userId)){
                return Result.error(HttpStatus.PermissionNotAllowed, "权限不足，无法查询：非本人发布任务");
            }else{
                //查询
                Integer count = taskReportService.queryTaskReportCount(taskId);
                return Result.success(count, "查询成功：管理员");
            }

        } else {//超管

            Integer count = taskReportService.queryTaskReportCount(taskId);
            return Result.success(count, "查询成功：超级管理员");
        }
    }



    /**
     *  通过taskId查询本人汇报记录 【userid后端获取】
     *  param: taskId
     * @param request
     * @param response
     * @return TaskReportVo
     */
    private Result queryMyReport(HttpServletRequest request, HttpServletResponse response){

        //获取请求参数
        String taskIdStr = request.getParameter("taskId");
        if (taskIdStr == null || taskIdStr.isEmpty()) {
            return Result.error(HttpStatus.IncomingDataError, "taskId不能为空");
        }

        Integer taskId;
        try {
            taskId = Integer.valueOf(taskIdStr);
        } catch (NumberFormatException e) {
            return Result.error(HttpStatus.IncomingDataError, "taskId必须为整数");
        }

        //获取TaskInfo对象
        TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskId);
        if(taskInfo==null){
            return Result.error(HttpStatus.NoDataFromDatabase,"该任务不存在");
        }

        //获取用户id
        UserLocal userMessage = getUserMessage(request, response);
        Integer userId = userMessage.getUserId();

        //将userId传入service层
        taskReportService.setUserId(userId);
        TaskReportVo taskReportVo;
        try {
            taskReportVo = taskReportService.queryByUserIdAndTaskId(taskId, userId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(HttpStatus.IncomingDataError,"该任务没有汇报记录");
        }
        TaskReport report = taskReportDao.selectByUserId(userId, taskId);

        TaskInfoForUser taskInfoForUser = new TaskInfoForUser();

        //发布者
        Integer publisherId = taskInfo.getPublisherId();
        String publisherName = taskGroupDao.findPublisherNameById(publisherId);

        //任务发布范围小组名称
        List<String> groupNames = taskGroupDao.findTaskGroupNamesByTaskId(taskId);

        //将数据封装进TaskInfoForUser对象
        taskInfoForUser.setId(taskInfo.getId());
        taskInfoForUser.setPublisherName(publisherName);
        taskInfoForUser.setGroupNames(groupNames);
        taskInfoForUser.setName(taskInfo.getName());
        taskInfoForUser.setContent(taskInfo.getContent());

        taskInfoForUser.setDealTime(taskInfo.getDealTime());
        taskInfoForUser.setPublishTime(taskInfo.getPublishTime());

        //任务状态根据截止时间判断
        Date now=new Date();
        if(now.after(taskInfo.getDealTime())){
            taskInfoForUser.setStatus(1);
        }else {
            taskInfoForUser.setStatus(0);
        }

        //是否汇报
        if(taskReportVo!=null){
            taskInfoForUser.setJudge(1);
        }else{
            taskInfoForUser.setJudge(0);
        }

        //任务状态
        taskInfoForUser.setStatus(report.getReportStatus());

        //汇报信息
        taskInfoForUser.setTaskReportVo(taskReportVo);

        if(taskReportVo!=null){
            return Result.success(taskInfoForUser,"成功查询");
        }else {
            return Result.error(HttpStatus.UnknowError,"未知错误");
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
        int cur = request.getParameter("cur") != null ? Integer.parseInt(request.getParameter("cur")) : 1;
        int size = request.getParameter("size") != null ? Integer.parseInt(request.getParameter("size")) : Integer.MAX_VALUE;
        //权限判断
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        //普通用户无法查看
        if (roleId==3){
            return Result.error(HttpStatus.PermissionNotAllowed,"普通用户不能查看汇报记录");
        }else if (roleId==2){
            //普通管理员
            //判断参数是否为空
                if (cur<0||size<0){
                    return Result.error(HttpStatus.IncomingDataError,"参数非法");
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
                if (taskInfoVos.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"暂未发布任务");
                }
                //将管理员发布的所有任务进行遍历，每次都查询该任务的所有所有汇报记录
                for (TaskInfoVo taskInfoVo:taskInfoVos){
                    try {
                        //每一次遍历会不会将前面的遍历覆盖？因为是直接将查询结果进行赋值，不会，因为最后让list集合successT进行了add操作
                        taskReportVos = taskReportService.queryAllTask(taskInfoVo.getId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    //如果本次循环中的任务汇报记录不是空的，将查询到的结果根据分页的要求，封装进入所要返回前端的list集合内
                    if (!taskReportVos.isEmpty()) {
                       if (cur!=1&&size!=Integer.MAX_VALUE){
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
                }//循环结束，判断所要返回给前端的内容中是否有内容
                //？？？ 如果没有内容的话，是否直接返回空的集合，而不用返回错误代码？？
                if (successT.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"所有任务暂时还没有汇报记录");
                }else {
//                    WebUtil.writeJson(response,Result.success(successT,"请求成功"));
                    return Result.success(successT,"请求成功");
                }

            }
            //输入taskId的情况下，id是否输入合法
            Integer userId = userMessage.getUserId();
            //临时调用dao层的方法，查询是否有该方法
            TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
            if (taskInfo==null){
                return Result.error(HttpStatus.IncomingDataError,"没有该任务");
            }
            //有该方法：
            //如果查询的用户是人物的发布者
            if (userId.equals(taskInfo.getPublisherId())){
                try {
                    //查询该任务的所有汇报记录
                    taskReportVos = taskReportService.queryAllTask(taskid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                //判断该任务是否有汇报记录
                if (taskReportVos.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"该任务暂时还没有汇报记录");
                }else {
                    //将查询到的任务按照分页要求进行分页
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur!=1&&size!=Integer.MAX_VALUE){
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
//                    WebUtil.writeJson(response,Result.success(taskInfoVoPage,"请求成功"));
                    return Result.success(taskInfoVoPage,"请求成功");
                }
            }
            else {//如果进行查询的用户不是人物的发布者而是其他管理员
                return Result.error(HttpStatus.PermissionNotAllowed,"没有权限查看他人任务的汇报记录");
            }
        }
        else {//权限是超级管理员
            //判断参数是否为空
                if (cur<0||size<0){
                    return Result.error(HttpStatus.IncomingDataError,"参数非法");
                }

            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            if (taskid!=null){//如果参数不是null，那么就查询指定任务的汇报记录
                try {
                    taskReportVos = taskReportService.queryAllTask(taskid);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (taskReportVos.isEmpty()){
                    return Result.error(HttpStatus.NO_CONTENT,"该任务暂时还没有汇报记录");
                }else {
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur!=1&&size!=Integer.MAX_VALUE){
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
//                    WebUtil.writeJson(response,Result.success(taskInfoVoPage,"请求成功"));
//                    return Result.success(taskInfoVoPage,"请求成功");
                    return Result.success(taskInfoVoPage,"请求成功");
                }
            }
            else {  //如果参数taskId为null，那么就是查询所有的任务的所有的汇报记录
                //临时编写sql语句进查询所有 有汇报记录的任务id
                BaseDao baseDao = new BaseDao();
                String str1 = """
                                select distinct task_id as taskId from task_report
                                """;
                List<Task>taskids=new ArrayList<>();
                taskids=baseDao.baseQuery(Task.class, str1);
                if (taskids.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"暂无汇报信息");
                }else {//如果不为空的话就就是有任务的汇报
                    List<TaskReportVo> SuccessTaskReportVos = new ArrayList<>();
                    //通过对有汇报记录的任务进行遍历，查询到有汇报记录的任务的所有汇报记录
                    for (Task id:taskids){
                        try {
                            taskReportVos = taskReportService.queryAllTask(id.getTaskId());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        SuccessTaskReportVos.addAll(taskReportVos);
                    }
                    List<TaskReportVo> taskInfoVoPage;
                    if (cur!=1&&size!=Integer.MAX_VALUE){
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
//                    WebUtil.writeJson(response,Result.success(taskInfoVoPage,"请求成功"));
                    return Result.success(taskInfoVoPage,"请求成功");
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
     * 直接将汇报记录删除了
     */
    private Result delete(HttpServletRequest request,HttpServletResponse response){
        //权限判断:只有管理员才能进行删除操作
        UserLocal userMessage = getUserMessage(request, response);
        Integer roleId = userMessage.getRoleId();
        Integer reportid=null;
        //读取参数
        String reportidStr = request.getParameter("reportid");
        if (reportidStr == null) {
            return Result.error(HttpStatus.IncomingDataError,"没用传入必要参数");
        } else {
            try {
                 reportid = Integer.parseInt(reportidStr);
            } catch (NumberFormatException e) {
                return Result.error(HttpStatus.IncomingDataError,"参数非法");
            }
        }
        //权限判断
        if (roleId == 3 ){
            return Result.error(HttpStatus.PermissionNotAllowed,"普通用户不能删除汇报记录");
        }else if (roleId==2){ //身份是普通管理员
            //判断是否有权限删除
            //根据reportid查出该任务的发布者
            Integer userId = userMessage.getUserId();
            Integer publisherId=taskReportService.queryuseridByreportid(reportid);
            if (publisherId==-1){
                return Result.error(HttpStatus.IncomingDataError,"无该汇报记录");
            }
            if (userId.equals(publisherId)){
                //执行删除任务
                //直接调用dao方法
                Integer i = taskReportDao.deleteByReportId(reportid);
                //检查执行结果
                if (i==1){
                    //成功找到任务，并且完成删除操作
                    resultMaker.setMsg("删除成功");
//                    WebUtil.writeJson(response,resultMaker);
                    return resultMaker;
                }else {
                    //未找到
                    return Result.error(HttpStatus.IncomingDataError,"未找到该汇报记录");
                }
            }else {
                return Result.error(HttpStatus.PermissionNotAllowed,"没有权限删除他人任务的汇报记录");
            }
        }else {   // 身份是超级管理员
            //直接调用dao方法
            Integer i = taskReportDao.deleteByReportId(reportid);
            //检查执行结果
            if (i==1){
                //成功找到任务，并且完成删除操作
                resultMaker.setMsg("删除成功");
//                WebUtil.writeJson(response,resultMaker);
                return resultMaker;
            }else {
                //未找到
                return Result.error(HttpStatus.IncomingDataError,"未找到该汇报记录");
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
        int cur = request.getParameter("cur") != null ? Integer.parseInt(request.getParameter("cur")) : 1;
        int size = request.getParameter("size") != null ? Integer.parseInt(request.getParameter("size")) : Integer.MAX_VALUE;

        if (roleId == 3 ){
            return Result.error(HttpStatus.PermissionNotAllowed,"普通用户不能查看所有需要汇报的用户");
        }else if (roleId==2){
            //管理员只能查看自己发布的需要汇报的用户信息
            //读取参数 cur size
            //taksid 输入非法： 不是自己发布的任务的id？？？？ 待处理
                if (cur<0||size<0){
                    return Result.error(HttpStatus.IncomingDataError,"参数非法");
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
                    if (!needReportUsers.isEmpty()) {
                        if (cur!=1&&size!=Integer.MAX_VALUE){
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
                if (successN.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"所有任务暂时还没有需要汇报的用户");
                }else {
//                    WebUtil.writeJson(response,Result.success(successN,"请求成功"));
                    return Result.success(successN,"请求成功");
                }
            }
            //taksid 输入非法： 不是自己发布的任务的id
            Integer userId = userMessage.getUserId();
            TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
            if(taskInfo==null){
                return Result.error(HttpStatus.IncomingDataError,"没有该任务");
            }
            if (!userId.equals(taskInfo.getPublisherId())){
                return Result.error(HttpStatus.PermissionNotAllowed,"没有权限查看他人任务的汇报用户");
            }else {
                //taskid->groupid->userid
                needReportUsers = taskReportService.queryAllUserForReport(taskid);
                if (needReportUsers.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"该任务没有汇报的用户");
                }else {
                    List<NeedReportUser> Page=new ArrayList<>();
                    if (cur!=1&&size!=Integer.MAX_VALUE){
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
//                    WebUtil.writeJson(response,Result.success(Page,"请求成功"));
                    return Result.success(Page,"请求成功");
                }
            }
        }else {
            //超级管理员
                if (cur<0||size<0){
                    return Result.error(HttpStatus.IncomingDataError,"参数非法");
                }

            if (request.getParameter("taskid")!=null){
                taskid = Integer.parseInt(request.getParameter("taskid"));
            }
            //超级管理员能查看所有发布的需要汇报的用户信息
           //超级管理员也可以输入id
            if (taskid!=null){
                //查询特定任务的汇报用户
                needReportUsers = taskReportService.queryAllUserForReport(taskid);
                if (needReportUsers.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"该任务没有汇报的用户");
                }else {
                    List<NeedReportUser> Page=new ArrayList<>();
                    if (cur!=1&&size!=Integer.MAX_VALUE){
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
//                    WebUtil.writeJson(response,Result.success(Page,"请求成功"));
                    return Result.success(Page,"请求成功");
                }
            }else {
                //查询所有任务的汇报用户
                //获取所有taskid
                BaseDao baseDao = new BaseDao();
                String str1 = """
                select id as taskId from task_info
                              """;
                List<Task>taskids=new ArrayList<>();
                taskids=baseDao.baseQuery(Task.class, str1);
                if (taskids.isEmpty()){
                    return Result.error(HttpStatus.NoDataFromDatabase,"暂无任务发布,没有可以汇报的用户");
                }else {
                    List<NeedReportUser> successN=new ArrayList<>();
                    for (Task i:taskids){
                         needReportUsers = taskReportService.queryAllUserForReport(i.getTaskId());
                        if (!needReportUsers.isEmpty()) {
                            if (cur!=1&&size!=Integer.MAX_VALUE){
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
                    if (successN.isEmpty()){
                        return Result.error(HttpStatus.NoDataFromDatabase,"所有任务暂时还没有需要汇报的用户");
                    }else {
//                        WebUtil.writeJson(response,Result.success(successN,"请求成功"));
                        return Result.success(successN,"请求成功");
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
       // UserLocal user = SecurityUtil.getUser();
        UserLocal user = new UserLocal();
        user.setUserName("JohnDoe");
        user.setRoleId(2);
        user.setUserId(2);
        return user;
    }
    protected void findMethod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 响应的MIME类型和乱码问题
        resp.setContentType("application/json;charset=UTF-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = null;
        try{
            //如果没有报错，就说明最后的一个参数是数字。
            Integer.parseInt(split[split.length - 1]);
            methodName = split[split.length - 2];
        }catch (NumberFormatException e){
            methodName =split[split.length - 1];
        }
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
        } catch (NullPointerException e1){
            e1.printStackTrace();
            result = Result.error(HttpStatus.NOT_FOUND,"缺少参数");
            WebUtil.writeJson(resp,result);
        }
        catch (Exception e2) {
            e2.printStackTrace();
            result = Result.error(HttpStatus.NOT_FOUND,"未找到该接口");
            WebUtil.writeJson(resp,result);
        }
    }
}

