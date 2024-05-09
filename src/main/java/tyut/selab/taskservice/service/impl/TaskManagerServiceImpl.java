package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dto.TaskManagerDto;
import tyut.selab.taskservice.service.TaskManagerService;
import tyut.selab.taskservice.view.TaskManagerVo;

import java.util.List;

public class TaskManagerServiceImpl implements TaskManagerService {

    /**
     *  增加任务
     * @param taskManagerDto
     * @return
     */

    public Integer insertTask(TaskManagerDto taskManagerDto){
        return null;
    }

    /**
     *   修改任务信息(管理员操作)
     * @param taskManagerDto
     * @return
     */
    public Integer updateTask(TaskManagerDto taskManagerDto){
        return null;
    }

    /**
     *  分页查询所有任务
     * @return
     */
    public List<TaskManagerDto> selectList(Integer cur,Integer size){
        return null;
    }

    /**
     *   通过 taskId 查询任务信息
     * @param taskId 任务id
     * @return
     */
    public TaskManagerVo selectTaskById(Integer taskId){
        return null;
    }

    /**
     *   通过任务名查询任务信息(支持模糊查询)
     * @param taskName
     * @return
     */
    public TaskManagerVo selectTaskByTaskName(String taskName){
        return null;
    }

    /**
     *  通过publisherId查询任务 （要不要分页）
     * @return
     */
    public List<TaskManagerVo> selectListByPublisherId(Integer publisherId){
        return null;
    }
}
