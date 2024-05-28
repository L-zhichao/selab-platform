package tyut.selab.taskservice.service;

import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;

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
     *  通过任务id和用户id查询汇报信息
     * @param taskId
     * @param userId
     * @return
     */
    public TaskReportVo queryByUserIdAndTaskId(Integer taskId,Integer userId);

    /**
     *  通过id查询任务的所有所有汇报情况
     * @param taskId
     * @return
     */
    public List<TaskInfoVo> queryAllTask(Integer taskId);

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


}
