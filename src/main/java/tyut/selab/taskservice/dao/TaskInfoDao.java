package tyut.selab.taskservice.dao;

import tyut.selab.taskservice.domain.TaskInfo;

import java.util.List;

/**
* @author 26580
* @description 针对表【task_info】的数据库操作Mapper
* @createDate 2024-05-10 10:44:51
* @Entity tyut.selab.taskservice.domain.TaskInfo
*/
public interface TaskInfoDao {


    /**
     *  删除任务信息
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);
    //此处的参数主键id就是任务id

    /**
     *  增加任务信息
     * @param record
     * @return
     */
    Integer insert(TaskInfo record);

    /**
     *  通过taskId查询任务信息
     * @param id
     * @return 返回null标识任务不存在
     */
    TaskInfo selectByTaskId(Integer id);

    /**
     *  修改任务信息
     * @param record
     * @return
     */
    Integer updateBytaskId(TaskInfo record);

    /**
     *  查询所有任务信息
     * @return 返回null标识当前数据库中还没有任务
     */
    List<TaskInfo> selectAllTaskInfo();

    /**
     *  查询未到截止时间的任务信息
     * @return
     */
    List<TaskInfo> selectByDealTimeTaskInfos();

}
