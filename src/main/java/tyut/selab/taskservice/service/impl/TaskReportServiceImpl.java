package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;

import java.util.List;

/**
 *
 */
public class TaskReportServiceImpl implements TaskReportService {


/**
 * 用于调用TaskReport的Dao层相关方法
 * */
    private TaskReportDao taskReportDao=new TaskReportDaoImpl();

    /**
     *  新增汇报记录
     * @param taskReportDto
     * @return
     */
    public Integer save(TaskReportDto taskReportDto){

        TaskReport taskReport = new TaskReport();

        taskReport.setTaskId(taskReportDto.getTaskId());
        taskReport.setReportStatus(taskReportDto.getReportStatus());
        taskReport.setDetails(taskReportDto.getDetails());
        taskReport.setReportId(null);
        taskReport.setCreateTime(null);

        return taskReportDao.insert(taskReport);
    }

    /**
     *  通过任务id和用户id查询汇报信息
     * @param taskId
     * @param userId
     * @return
     */
    public TaskReportVo queryByUserIdAndTaskId(Integer taskId,Integer userId){
        return null;
    }

    /**
     *  通过id查询任务的所有所有汇报情况
     * @param taskId
     * @return
     */
    public List<TaskReportVo> queryAllTask(Integer taskId){
        return null;
    }

    /**
     *  通过id查询某一任务汇报数量
     * @param taskId
     * @return
     */
    public Integer queryTaskReportCount(Integer taskId){
        return null;
    }

    /**
     *  通过id查询所有用户
     * @param taskId
     * @return
     */
    public List<NeedReportUser> queryAllUserForReport(Integer taskId){
        return null;
    }


}