package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskReportDao;
import tyut.selab.taskservice.dao.impl.TaskReportDaoImpl;
import tyut.selab.taskservice.domain.TaskReport;
import tyut.selab.taskservice.dto.NeedReportUser;
import tyut.selab.taskservice.dto.TaskReportDto;
import tyut.selab.taskservice.service.TaskReportService;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskReportVo;
import tyut.selab.userservice.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
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


    @Override
    public TaskReportVo queryByUserIdAndTaskId(Integer taskId, Integer userId) {
        return null;
    }

    @Override
    public List queryAllTask(Integer taskId) throws SQLException {
        List<TaskReportVo> taskReportVos=new ArrayList<>();
        BaseDao baseDao=new BaseDao();
        //调用dao层方法 //异常未处理
         List<TaskReport> taskReports = taskReportDao.selectByTaskIdTaskReports(taskId);
        //将TaskReport封装成TaskReportVo对象
        for (TaskReport taskReport:taskReports){
            TaskReportVo taskReportVo=new TaskReportVo();
            taskReportVo.setTaskId(taskReport.getTaskId());
            taskReportVo.setReportId(taskReport.getReportId());
            taskReportVo.setReportStatus(taskReport.getReportStatus());
            taskReportVo.setDetails(taskReport.getDetails());
            taskReportVo.setReportTime(taskReport.getCreateTime());
            //如何获取用户的名字 taskReportVo.setUserName(); sys_user内存有用户的名字
            String sql = """
                     select user_name userName from sys_user where user_id = ?
                      """;
            User UserName = baseDao.baseQueryObject(User.class, sql,taskReport.getUserId());
            taskReportVo.setUserName(UserName.getUserName()); taskReportVos.add(taskReportVo); }
         return taskReportVos;
    }

    /**
     *  通过id查询某一任务汇报数量--??????
     * */
    @Override
    public Integer queryTaskReportCount(Integer taskId) {


        return null;
    }

    @Override
    public List<NeedReportUser> queryAllUserForReport(Integer taskId) {
        return null;
    }
}
