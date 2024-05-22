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
 * (TaskReport)表服务实现类
 *
 * @author makejava
 * @since 2024-05-21 20:01:02
 */

public class TaskReportServiceImpl implements TaskReportService {

    private TaskReportDao taskReportDao = new TaskReportDaoImpl();

    /**
     * 通过ID查询单条数据
     *
     //* @param reportId 主键
     * @return 实例对象
     */
//    @Override
//    public TaskReport queryById(Integer reportId) {
//        return this.taskReportDao.queryById(reportId);
//    }
//
//    /**
//     * 分页查询
//     *
//     * @param taskReport 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<TaskReport> queryByPage(TaskReport taskReport, PageRequest pageRequest) {
//        long total = this.taskReportDao.count(taskReport);
//        return new PageImpl<>(this.taskReportDao.queryAllByLimit(taskReport, pageRequest), pageRequest, total);
//    }

//    /**
//     * 新增数据
//     *
//     * @param taskReport 实例对象
//     * @return 实例对象
//     */
//    @Override
//    public TaskReport insert(TaskReport taskReport) {
//        this.taskReportDao.insert(taskReport);
//        return taskReport;
//    }

//    /**
//     * 修改数据
//     *
//     * @param taskReport 实例对象
//     * @return 实例对象
//     */
////    @Override
////    public TaskReport update(TaskReport taskReport) {
//        this.taskReportDao.update(taskReport);
//        return this.queryById(taskReport.getReportId());
//    }
//
//    /**
//     * 通过主键删除数据
//     *
//     * @param reportId 主键
//     * @return 是否成功
//     */
//    @Override
//    public boolean deleteById(Integer reportId) {
//        return this.taskReportDao.deleteById(reportId) > 0;
//    }

    @Override
    public Integer save(TaskReportDto taskReportDto) {
        return null;
    }

    @Override
    public TaskReportVo queryByUserIdAndTaskId(Integer taskId, Integer userId) {
        return null;
    }

    @Override
    public List<TaskReportVo> queryAllTask(Integer taskId) throws SQLException {
        return null;
    }

    @Override
    public Integer queryTaskReportCount(Integer taskId) {
        return null;
    }

    @Override
    public List<NeedReportUser> queryAllUserForReport(Integer taskId) {
        return null;
    }
}
