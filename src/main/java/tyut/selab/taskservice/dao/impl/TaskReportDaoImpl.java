package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.myutils.Task;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.userservice.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
* @author lzc
* @description 针对表【task_report】的数据库操作Mapper
* @createDate 2024-05-10 11:02:14
* @Entity tyut.selab.taskservice.domain.TaskReport
*/
public class TaskReportDaoImpl  extends BaseDao implements TaskReportDao {


    public Integer deleteByPrimaryKey(Long id){

        return null;
    }

    /**
     *  增加汇报信息
     * @param record
     * @return
     */
    public Integer insert(TaskReport record){

        Object[] report =new Object[]{
                record.getTaskId(),
                record.getUserId(),
                record.getReportStatus(),
                record.getDetails(),
                record.getCreateTime()
        };

        String sql1="select report_id from task_report where task_id=? and user_id=? and report_status=? and details=? and create_time=?";
        String sql="INSERT INTO task_reports (task_id, user_id, report_status, details, create_time) VALUES (?, ?, ?, ?, ?)";

        Integer rowsAffected;
        try {
            rowsAffected = baseUpdate(sql, report);
        }catch (RuntimeException e){
            throw e;
        }
        return rowsAffected;
    }

    /**
     * 通过用户id和taskId查询汇报信息（查询本人汇报记录)
     *
     * @param userId
     * @return
     */
    public List<TaskReport> selectByUserId(Integer userId, Integer taskId){

        TaskReport taskReport = new TaskReport();
        String sql="select from task_report where user_id=? and task_id=?";
       List<TaskReport> reports =  baseQuery(TaskReport.class, sql, userId, taskId);
        return reports;
    }

    /**
     *  通过taskId查询所有汇报信息
     * @param taskId
     * @return
     */
    public List<TaskReport> selectByTaskIdTaskReports(Integer taskId)  {
        List<TaskReport> taskReports=new ArrayList<>();
        String sql1="select report_id as reportId,task_id as taskId,user_id as userId,report_status as reportStatus,details,create_time as createTime from task_report where task_id=?";
        taskReports = baseQuery(TaskReport.class, sql1, taskId);
        return taskReports;
    }

    /**
     *  通过任务id查询所有汇报用户
     * @param taskId
     * @return 暂时返回的是用户的id(待更改)
     */
    public List<Integer> selectByTaskIdForUserId(Integer taskId){
        TaskGroupDaoImpl taskGroupDao=new TaskGroupDaoImpl();
        List<Integer> integers = new ArrayList<>();
        //获取对应任务的小组
        List<TaskGroup> taskGroups = taskGroupDao.selectAllTaskGroupsByTaskId(taskId);
        //获取小组的对应成员id
        String str="select user_id as userId from user_group where group_id=?";
        for (TaskGroup group :taskGroups){
            List<Task> integerss = baseQuery(Task.class, str, group.getGroupId());
//            integers.addAll(integerss);
            for (Task t:integerss){
                integers.add(t.getUserId());
            }
        }
        return integers;
    }

    /**
     *通过id查询某一任务
     * @param id
     * @return
     */
    public TaskReport selectByReportId(Long id){

        String sql="SELECT * FROM task_report WHERE report_id = ?";
        TaskReport taskReport = new TaskReport();
        return baseQueryObject(TaskReport.class,sql,id);

    }

    /**
     * 更新汇报记录
     * @param record
     * @return
     */
    public Integer updateByReportId(TaskReport record){

            String sql1="SELECT * FROM task_report WHERE report_id = ?";
            String sql = "UPDATE task_report SET task_id = ?, report_status = ? WHERE details = ?";

            Object[] report = new Object[]{
                    record.getTaskId(),
                    record.getReportStatus(),
                    record.getDetails()
            };

            return baseUpdate(sql, report);


    }

    /**
     *
     * @param reportId
     * @return
     */
    public Integer deleteByReportId(Integer reportId){
String sql= """
        DELETE FROM task_report WHERE report_id = ?;
        """;
        Integer i = baseUpdate(sql, reportId);
        //成功删除返回1
        return i;
    }


    /**
     * 通过id查询某一任务汇报数量
     * @param taskId
     * @return
     * */
    @Override
    public Integer queryTaskReportCount(Integer taskId) {
       // String sql="SELECT COUNT(*) AS reports_count FROM task_reports where task_id=?";

        String sql="SELECT * FROM task_report WHERE report_id = ?";
        List<TaskReport> reports = baseQuery(TaskReport.class, sql, taskId);
        Integer taskReportCount=reports.size();
        return taskReportCount;

    }
    /**
     * 通过reportid查询某一任务的taskid
     * @param reportId
     * @return taskid
     * */
    @Override
    public Integer queryTaskIdByrid(Integer reportId){
        String sql1="select task_id as taskId from task_report where report_id=?";
        TaskReport task = baseQueryObject(TaskReport.class, sql1, reportId);
        if (task!=null){
            return task.getTaskId();
        }else {
            return -1;
        }
    }

    @Override
    public Integer conflict(TaskReportDto taskReportDto) {
        String sql="select count(*) from task_report where task_id =? and report_status=? and details=?";
        TaskInfo taskInfo = baseQueryObject(TaskInfo.class, sql, taskReportDto.getTaskId(), taskReportDto.getReportStatus(), taskReportDto.getDetails());
        if(taskInfo!=null){
            return 1;
        }else {
            return 0;
        }
    }

/**
 * 通过userId获取userName
 * */
    @Override
    public String getUserNameByUserId(Integer userId) {
        String sql="select user_name from sys_user where user_id=?";
        return queryForObject(sql,userId);
    }
}
