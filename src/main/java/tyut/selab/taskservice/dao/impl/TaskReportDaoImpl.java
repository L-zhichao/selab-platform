package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.myutils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
        String sql1="select report_id from task_report where task_id=? and user_id=? and report_status=? and details=? and create_time=?";
        String sql="INSERT INTO task_reports (task_id, user_id, report_status, details, create_time) VALUES (?, ?, ?, ?, ?)";

        Object[] report =new Object[]{
                record.getTaskId(),
                record.getUserId(),
                record.getReportStatus(),
                record.getDetails(),
                record.getCreateTime()
        };

        return baseUpdate(sql,report);
    }

    /**
     *  通过用户id和taskId查询汇报信息
     * @param userId
     * @return
     */
    public TaskReport selectByUserId(Integer userId, Integer taskId){
        return null;
    }

    /**
     *  通过taskId查询所有汇报信息
     * @param taskId
     * @return
     */
    public List<TaskReport> selectByTaskIdTaskReports(Integer taskId)  {
        List<TaskReport> taskReports=new ArrayList<>();
        String sql1="select * from task_report where task_id=?";

        //用于查询当前taskId的汇报数量
        String sql2="SELECT COUNT(*) AS total_reports from task_report where task_id=?";

        taskReports = baseQuery(TaskReport.class, sql1, taskId);
        return taskReports;
    }

    /**
     *  通过任务id查询所有汇报用户
     * @param taskId
     * @return 暂时返回的是用户的id(待更改)
     */
    public List<Integer> selectByTaskIdForUserId(Integer taskId){
        String sql1="select user_id from task_report where task_id=?";
        List<Integer> integers = baseQuery(Integer.class, sql1, taskId);
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
        DELETE FROM users WHERE report_id = ?;
        """;
        Integer i = baseUpdate(sql, reportId);
        //成功删除返回1
        return i;
    }

}
