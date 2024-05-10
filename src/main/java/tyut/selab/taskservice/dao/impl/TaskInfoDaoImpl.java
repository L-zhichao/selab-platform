package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.domain.TaskInfo;

import java.util.List;

/**
* @author 26580
* @description 针对表【task_info】的数据库操作Mapper
* @createDate 2024-05-10 10:44:51
* @Entity tyut.selab.taskservice.domain.TaskInfo
*/
public class TaskInfoDaoImpl implements TaskInfoDao {


    /**
     *  删除任务信息
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Long id){
        return null;
    }

    /**
     *  增加任务信息
     * @param record
     * @return
     */
    public Integer insert(TaskInfo record){
        return null;
    }

    /**
     *  通过taskId查询任务信息
     * @param id
     * @return
     */
    public TaskInfo selectByTaskId(Integer id){
        return null;
    }

    /**
     *  修改任务信息
     * @param record
     * @return
     */
    public Integer updateBytaskId(TaskInfo record){
        return null;
    }

    /**
     *  查询所有任务信息
     * @return
     */
    public List<TaskInfo> selectAllTaskInfo(){
        return null;
    }

    /**
     *  查询未到截止时间的任务信息
     * @return
     */
    public List<TaskInfo> selectByDealTimeTaskInfos(){
        return null;
    }

}
