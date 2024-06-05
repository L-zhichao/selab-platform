package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.myutils.Task;
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

        //查重
//        String sqlCheck="select from task_report where task_id=? and user_id=? and report_status=? and details=?";
//        TaskReport sameTaskReport = baseQueryObject(TaskReport.class, sqlCheck,record.getTaskId(),record.getUserId(), record.getReportStatus(), record.getDetails());
//        if(sameTaskReport!=null){
//            throw new RuntimeException("Same report exists");
//        }

        String sqlInsert="INSERT INTO task_report (report_id,task_id, user_id, report_status, details, create_time) VALUES (?, ?, ?, ?, ?, ?)";

        Integer rowsAffected=null;
        try {
            rowsAffected = baseUpdate(sqlInsert,record.getReportId(),record.getTaskId(),record.getUserId(), record.getReportStatus(), record.getDetails(), record.getCreateTime());
            return rowsAffected;
        }catch (RuntimeException e){
            throw e;
        }
    }

    /**
     * 通过用户id和taskId查询汇报信息（查询本人汇报记录)
     * 只返回最新一条汇报信息
     * @param userId
     * @return
     */
    public TaskReport selectByUserId(Integer userId, Integer taskId){

        String sql = """
                    select report_id as reportId,task_id as taskId,user_id as userId,report_status as reportStatus,details,create_time as createTime
                    from task_report
                    where user_id=? and task_id=?
                    order by report_id desc
                    limit 1
                    """;

        List<TaskReport> reports = baseQuery(TaskReport.class, sql, userId, taskId);
        if (reports != null && !reports.isEmpty()) {
            return reports.get(0); // 返回列表中的第一个元素，即最新的记录
        }
        return null;
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
           return null;
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
      String sql="SELECT COUNT(*) AS reports_count FROM task_report WHERE task_id=?";

        return baseCountQuery(sql, taskId);
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



/**
 * 通过userId获取userName
 */

    public String getUserNameByUserId(Integer userId) {
        String sql="select user_name as userName from sys_user where user_id=?";
        User user = baseQueryObject(User.class, sql,userId);
        String userName= user.getUserName();
        if(userName!=null){
            return userName;
        }else {
            throw new RuntimeException("User not found");
        }
    }

}
