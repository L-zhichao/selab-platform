package tyut.selab.taskservice.service;

import tyut.selab.taskservice.dto.TaskManagerDto;
import tyut.selab.taskservice.view.TaskManagerVo;

import java.util.List;

public interface TaskManagerService {

    /**
     *  增加任务
     * @param taskManagerDto
     * @return
     */

    public Integer insertTask(TaskManagerDto taskManagerDto);

    /**
     *   修改任务信息(管理员操作)
     * @param taskManagerDto
     * @return
     */
    public Integer updateTask(TaskManagerDto taskManagerDto);

    /**
     *  分页查询所有任务
     * @return
     */
    public List<TaskManagerDto> selectList(Integer cur,Integer size);

    /**
     *   通过 taskId 查询任务信息
     * @param taskId 任务id
     * @return
     */
    public TaskManagerVo selectTaskById(Integer taskId);

    /**
     *   通过任务名查询任务信息(支持模糊查询)
     * @param taskName
     * @return
     */
    public TaskManagerVo selectTaskByTaskName(String taskName);

    /**
     *  通过publisherId查询任务 （要不要分页）
     * @return
     */
    public List<TaskManagerVo> selectListByPublisherId(Integer publisherId);
}
