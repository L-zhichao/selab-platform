package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.domain.TaskReport;
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
        String sql1="select * from task_report where id=?";
        taskReports = baseQuery(TaskReport.class, sql1, taskId);
        return taskReports;
    }

    /**
     *  通过任务id查询所有汇报用户
     * @param taskId
     * @return
     */
    public List<Integer> selectByTaskIdForUserId(Integer taskId){
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public TaskReport selectByReortId(Long id){
        return null;
    }

    /**
     *
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
        DELETE FROM users WHERE reportid = ?;
        """;
        Integer i = baseUpdate(sql, reportId);
        return i;
    }

}
