package tyut.selab.taskservice.dao;

import tyut.selab.taskservice.domain.TaskReport;

import java.util.List;

/**
* @author lzc
* @description 针对表【task_report】的数据库操作Mapper
* @createDate 2024-05-10 11:02:14
* @Entity tyut.selab.taskservice.domain.TaskReport
*/
public interface TaskReportDao {


    Integer deleteByPrimaryKey(Long id);

    /**
     *  增加汇报信息
     * @param record
     * @return
     */
    Integer insert(TaskReport record);

    /**
     *  通过用户id和taskId查询汇报信息
     * @param userId
     * @return
     */
    TaskReport selectByUserId(Integer userId,Integer taskId);

    /**
     *  通过taskId查询所有汇报信息
     * @param taskId
     * @return
     */
    List<TaskReport> selectByTaskIdTaskReports(Integer taskId);

    /**
     *  通过任务id查询所有汇报用户
     * @param taskId
     * @return
     */
    List<Integer> selectByTaskIdForUserId(Integer taskId);

    /**
     *
     * @param id
     * @return
     */
    TaskReport selectByReortId(Long id);

    /**
     *
     * @param record
     * @return
     */
    Integer updateByReportId(TaskReport record);

    /**
     *
     * @param reportId
     * @return
     */
    Integer deleteByReportId(Integer reportId);

}
