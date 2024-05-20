package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class TaskReportServiceImpl implements TaskReportService {
    //用于调用Dao层TaskReportDaoImpl的方法
    private TaskReportDao taskReportDao=new TaskReportDaoImpl();

    /**
     *  新增汇报记录
     * @param taskReportDto
     * @return
     */
    public Integer save(TaskReportDto taskReportDto){
        return null;
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
    public List<TaskReportVo> queryAllTask(Integer taskId) throws SQLException {
        //调用dao层方法
        //异常处理
        List<TaskReport> taskReports = taskReportDao.selectByTaskIdTaskReports(taskId);
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
