package tyut.selab.taskservice.service.impl;

//import org.junit.platform.commons.util.StringUtils;
import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.view.TaskInfoForUser;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.userservice.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 *
 */

public class TaskReportServiceImpl implements TaskReportService {


    /**
     * 用于调用TaskReport的Dao层相关方法
     * */
    private TaskReportDao taskReportDao=new TaskReportDaoImpl();
    private Integer userId;
    /**
     *  新增汇报记录
     * @param taskReportDto
     * @return
     */

    //如何获取用户id
    public Integer save(TaskReportDto taskReportDto) {

        // 验证输入参数
        if (taskReportDto == null) {
            throw new IllegalArgumentException("任务汇报对象不能为空");
        }
        if (taskReportDto.getTaskId() == null) {
            throw new IllegalArgumentException("任务id不能为空");
        }
        if (taskReportDto.getReportStatus() == null) {
            throw new IllegalArgumentException("汇报状态不能为空");
        }
        if (taskReportDto.getDetails()==null) {
            throw new IllegalArgumentException("汇报信息不能为空");
        }

        //查重
        int isConflict = taskReportDao.conflict(taskReportDto);
        if(isConflict==1){
            throw new RuntimeException("存在相同汇报信息");
        }

        // 创建TaskReport对象
        TaskReport taskReport = new TaskReport();

        taskReport.setTaskId(taskReportDto.getTaskId());
        taskReport.setReportStatus(taskReportDto.getReportStatus());
        taskReport.setDetails(taskReportDto.getDetails());
       //获取汇报人id
        if(userId!=null){
            taskReport.setUserId(userId);
        }else {
            throw new RuntimeException("用户id为空，无法执行操作");
        }

        // 自动设置createTime
        taskReport.setCreateTime(new Date());

        try {
            return taskReportDao.insert(taskReport);
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     *  通过任务id和用户id查询汇报信息
     */
    @Override
    public List<TaskReportVo> queryByUserIdAndTaskId(Integer taskId, Integer userId) {

        List<TaskReportVo> taskReportVos = new ArrayList<>();
        List<TaskReport> taskReports = null;
        if(taskId==null){
            throw new IllegalArgumentException("任务id不能为空");
        }
        try {
            taskReports=taskReportDao.selectByUserId(userId,taskId);
        }catch (Exception e){
            throw new RuntimeException();
        }


    //将TaskReport封装成TaskReportVo对象
        for(TaskReport taskReport : taskReports){
            TaskReportVo taskReportVo = new TaskReportVo();
            taskReportVo.setTaskId(taskReport.getTaskId());
            taskReportVo.setReportId(taskReport.getReportId());
            taskReportVo.setReportStatus(taskReport.getReportStatus());
            taskReportVo.setDetails(taskReport.getDetails());
            taskReportVo.setReportTime(taskReport.getCreateTime());

            taskReportVos.add(taskReportVo);
        }

        return taskReportVos;
    }

    @Override
    public List<TaskReportVo> queryAllTask(Integer taskId) {
        List<TaskReportVo> taskReportVos = new ArrayList<>();
        BaseDao baseDao = new BaseDao();
        // 调用dao层方法
        List<TaskReport> taskReports = null;
        try {
            taskReports = taskReportDao.selectByTaskIdTaskReports(taskId);
        } catch (SQLException e) {
            e.printStackTrace();
            return taskReportVos;  // 如果发生异常，返回空列表
        }
        String sql = """
                select user_name from sys_user where user_id = ?
                """;
        // 将TaskReport封装成TaskReportVo对象
        for (TaskReport taskReport : taskReports) {
            TaskReportVo taskReportVo = new TaskReportVo();
            taskReportVo.setTaskId(taskReport.getTaskId());
            taskReportVo.setReportId(taskReport.getReportId());
            taskReportVo.setReportStatus(taskReport.getReportStatus());
            taskReportVo.setDetails(taskReport.getDetails());
            taskReportVo.setReportTime(taskReport.getCreateTime());
            // 如何获取用户的名字 taskReportVo.setUserName(); sys_user内存有用户的名字
            User userName = baseDao.baseQueryObject(User.class, sql, taskReport.getUserId());
            if (userName != null) {
                taskReportVo.setUserName(userName.getUserName());
            }//user
            taskReportVos.add(taskReportVo);
        }
        return taskReportVos;
    }

    /**
     *  通过id查询某一任务汇报数量
     * */
    @Override
    public Integer queryTaskReportCount(Integer taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("任务id不能为空");
        }
        Integer count = taskReportDao.queryTaskReportCount(taskId);
        return count;
    }

    @Override
    public List<NeedReportUser> queryAllUserForReport(Integer taskId) {
        List<NeedReportUser> needReportUsers=new ArrayList<>();
        BaseDao baseDao = new BaseDao();
        List<Integer> userIds = taskReportDao.selectByTaskIdForUserId(taskId);
        //userid 变成 username
        String sql = """
                select user_name  from sys_user where user_id = ?
                """;
        String sql1 = """
                select report_id  from task_report where user_id = ? and task_id=?
                """;
        for (Integer i:userIds){
            User userName = baseDao.baseQueryObject(User.class, sql,i);
            Integer i1 = baseDao.baseQueryObject(Integer.class, sql1, i, taskId);
            NeedReportUser needReportUser=new NeedReportUser();
            if (userName != null) {
                needReportUser.setUserName(userName.getUserName());
                if (i1!=null){
                    needReportUser.setIsReport(1);//在汇报表中存在 代表以及汇报过
                }else {
                    needReportUser.setIsReport(0);
                }
                needReportUsers.add(needReportUser);
            }//user
        }
        return needReportUsers;
    }
    /**
     *  通过reportid查询该任务发布者的id
     * @param reportid
     * @return userid
     */
    @Override
public Integer queryuseridByreportid(Integer reportid){
    //调用TaskServiceImpl方法
    TaskInfoDao taskInfoDao=new TaskInfoDaoImpl();
    Integer taskid=taskReportDao.queryTaskIdByrid(reportid);
    TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
    return taskInfo.getPublisherId();
}

    public void setUserId(Integer userId){
        this.userId=userId;
    }
}
