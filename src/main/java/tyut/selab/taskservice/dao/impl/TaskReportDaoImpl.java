package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.myutils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        return null;
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
        String sql1="select id from selab_platform where id=?";
        List<TaskReport> taskReports = baseQuery(TaskReport.class, sql1, taskId);
        return null;
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
        return null;
    }

}
