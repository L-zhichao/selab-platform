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
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.myutils.Task;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.view.TaskInfoForUser;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.userservice.domain.User;

import java.sql.SQLException;
import java.util.*;


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
            Integer insert = taskReportDao.insert(taskReport);
            if(insert!=null){
                taskReport.setReportStatus(1);
            }
            return insert;
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     *  通过任务id和用户id查询汇报信息
     */
    @Override
    public TaskReportVo queryByUserIdAndTaskId(Integer taskId, Integer userId) {

        TaskReport taskReport = null;
        if(taskId==null||userId==null){
            throw new IllegalArgumentException("任务id和用户id都不能为空");
        }

        try {
            taskReport=taskReportDao.selectByUserId(userId,taskId);
        }catch (RuntimeException e){
            throw new RuntimeException("查询汇报记录时出错",e);
        }

        return ToTaskReportVo(taskReport);
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
            return taskReportVos;  // 如果发生异常，返回空列表
        }
        if (taskReports == null) {
            return taskReportVos;  // 如果taskReports为null，返回空列表
        }
        String sql = """
            select user_name as userName from sys_user where user_id = ?
            """;
        // 将TaskReport封装成TaskReportVo对象
        for (TaskReport taskReport : taskReports) {
            if (taskReport == null) {
                continue;  // 如果taskReport为null，跳过这次循环
            }
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
        return taskReportDao.queryTaskReportCount(taskId);
    }

    @Override
    public List<NeedReportUser> queryAllUserForReport(Integer taskId) {
        List<NeedReportUser> needReportUsers=new ArrayList<>();
        BaseDao baseDao = new BaseDao();
        List<Integer> userIds = taskReportDao.selectByTaskIdForUserId(taskId);
        //userid 变成 username
        String sql = """
                select user_name as userName  from sys_user where user_id = ?
                """;
        String sql1 = """
                select report_id as reportId  from task_report where user_id = ? and task_id=?
                """;
        for (Integer i:userIds){
            User userName = baseDao.baseQueryObject(User.class, sql,i);
            Task i1 = baseDao.baseQueryObject(Task.class, sql1, i, taskId);
            NeedReportUser needReportUser=new NeedReportUser();
            if (userName != null) {
                needReportUser.setUserName(userName.getUserName());
                if (i1 != null){
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
    if (taskid==-1){
        return -1;
    }
    TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskid);
    return taskInfo.getPublisherId();
}

/**
 * 用于从controller层接收userId
 * */
    public void setUserId(Integer userId){
        this.userId=userId;
    }

    /**
     * 将taskReport转化为taskReportVo
     * */
    private TaskReportVo ToTaskReportVo(TaskReport taskReport){
        if(taskReport==null){
            throw new RuntimeException("任务汇报对象为空");
        }

        //查询用户名
        String userName = taskReportDao.getUserNameByUserId(userId);

        //将TaskReport封装成TaskReportVo对象
        TaskReportVo taskReportVo = new TaskReportVo();

        taskReportVo.setReportId(taskReport.getReportId());
        taskReportVo.setTaskId(taskReport.getTaskId());
        taskReportVo.setUserName(userName);
        taskReportVo.setReportStatus(taskReport.getReportStatus());
        taskReportVo.setDetails(taskReport.getDetails());
        taskReportVo.setReportTime(taskReport.getCreateTime());

        return taskReportVo;
    }

}
