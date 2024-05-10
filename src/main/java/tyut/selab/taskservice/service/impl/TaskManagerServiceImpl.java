package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dto.TaskDto;
import tyut.selab.taskservice.service.TaskManagerService;
import tyut.selab.taskservice.view.TaskrVo;

import java.util.List;

public class TaskManagerServiceImpl implements TaskManagerService {

    /**
     *  增加任务
     * @param taskManagerDto
     * @return
     */

    public Integer insertTask(TaskDto taskManagerDto){
        return null;
    }

    /**
     *   修改任务信息(管理员操作)
     * @param taskManagerDto
     * @return
     */
    public Integer updateTask(TaskDto taskManagerDto){
        return null;
    }

    /**
     *  分页查询所有任务
     * @return
     */
    public List<TaskDto> selectList(Integer cur, Integer size){
        return null;
    }

    /**
     *   通过 taskId 查询任务信息
     * @param taskId 任务id
     * @return
     */
    public TaskrVo selectTaskById(Integer taskId){
        return null;
    }

    /**
     *   通过任务名查询任务信息(支持模糊查询)
     * @param taskName
     * @return
     */
    public TaskrVo selectTaskByTaskName(String taskName){
        return null;
    }

    /**
     *  通过publisherId查询任务 （要不要分页）
     * @return
     */
    public List<TaskrVo> selectListByPublisherId(Integer publisherId){
        return null;
    }
}
