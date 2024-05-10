package tyut.selab.taskservice.service;

import tyut.selab.taskservice.dto.TaskDto;
import tyut.selab.taskservice.view.TaskrVo;

import java.util.List;

public interface TaskManagerService {

    /**
     *  增加任务
     * @param taskManagerDto
     * @return
     */

    public Integer insertTask(TaskDto taskManagerDto);

    /**
     *   修改任务信息(管理员操作)
     * @param taskManagerDto
     * @return
     */
    public Integer updateTask(TaskDto taskManagerDto);

    /**
     *  分页查询所有任务
     * @return
     */
    public List<TaskDto> selectList(Integer cur, Integer size);

    /**
     *   通过 taskId 查询任务信息
     * @param taskId 任务id
     * @return
     */
    public TaskrVo selectTaskById(Integer taskId);

    /**
     *   通过任务名查询任务信息(支持模糊查询)
     * @param taskName
     * @return
     */
    public TaskrVo selectTaskByTaskName(String taskName);

    /**
     *  通过publisherId查询任务 （要不要分页）
     * @return
     */
    public List<TaskrVo> selectListByPublisherId(Integer publisherId);
}
