package tyut.selab.taskservice.service;

import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.view.TaskInfoForUser;
import tyut.selab.taskservice.view.TaskReportVo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface TaskReportService {

    /**
     *  新增汇报记录
     * @param taskReportDto
     * @return
     */
    public Integer save(TaskReportDto taskReportDto);

    /**
     * 通过任务id和用户id查询汇报信息
     *
     * @param taskId
     * @param userId
     * @return
     */
    public List<TaskReportVo> queryByUserIdAndTaskId(Integer taskId, Integer userId);

    /**
     *  通过id查询任务的所有所有汇报情况
     * @param taskId
     * @return
     */
    public List<TaskReportVo>  queryAllTask(Integer taskId) throws SQLException;

    /**
     *  通过id查询某一任务汇报数量
     * @param taskId
     * @return
     */
    public Integer queryTaskReportCount(Integer taskId);

    /**
     *  通过id查询所有用户
     * @param taskId
     * @return
     */
    public List<NeedReportUser> queryAllUserForReport(Integer taskId);
    /**
     *  通过reportid查询该任务发布者的id
     * @param reportid
     * @return userid
     */
    public Integer queryuseridByreportid(Integer reportid);



}
