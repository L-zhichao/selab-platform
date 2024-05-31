package tyut.selab.taskservice.dao;

import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.dto.TaskReportDto;

import java.sql.SQLException;
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
     * 通过用户id和taskId查询汇报信息
     *
     * @param userId
     * @return
     */
   TaskReport selectByUserId(Integer userId, Integer taskId);

    /**
     *  通过taskId查询所有汇报信息
     * @param taskId
     * @return
     */
    List<TaskReport> selectByTaskIdTaskReports(Integer taskId) throws SQLException;

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
    TaskReport selectByReportId(Long id);

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


    /**
     * 通过id查询某一任务汇报数量
     * */
    Integer queryTaskReportCount(Integer taskId);

    /**
     * 通过reportid查询某一任务的taskid
     * @param reportId
     * @return taskid
     * */
    public Integer queryTaskIdByrid(Integer reportId);

    /**
     * 根据传入的taskReportDto对象判断在数据库中是否冲突
     * @param taskReportDto
     * @return 返回1 标识任务和现有的任务冲突，返回0 标识不冲突
     */
    Integer conflict (TaskReportDto taskReportDto);

    /**
     * 通过userId获取userName
     * 针对【sys_user】表
     * @param userId
     * @return userName
     * */
    public String getUserNameByUserId(Integer userId);

}
