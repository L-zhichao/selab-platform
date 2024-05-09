package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dto.TaskManagerDto;
import tyut.selab.taskservice.view.TaskManagerVo;

import java.util.List;

public class TaskManagerDaoImpl {

    /**
     *  增加一个任务管理
     * @param taskManagerDto
     * @return
     */
    Integer insert(TaskManagerDto taskManagerDto){
        return null;
    }

    /**
     *  修改任务管理信息
     * @param taskManagerDto
     * @return
     */
    Integer update(TaskManagerDto taskManagerDto){
        return null;
    }

    /**
     * 通过id查询书籍信息
     * @param taskId
     * @return
     */
    TaskManagerVo selectByTaskId(Integer taskId){
        return null;
    }

    /**
     *  查询发布人的发布的所有任务
     * @param publisherId
     * @return
     */
    List<TaskManagerVo> selectByPublisherId(Integer publisherId){
        return null;
    }

    /**
     * 查询所有任务
     * @return
     */
    List<TaskManagerVo> selectAll(){
        return null;
    }

    /**
     * 通过任务名称<模糊查询>任务信息
     * @return
     */
    List<TaskManagerVo> selectAllByTaskName(){
        return null;
    }
}
