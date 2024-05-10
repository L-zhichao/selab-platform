package tyut.selab.taskservice.dao;

import tyut.selab.taskservice.domain.TaskGroup;

import java.util.List;

/**
* @author lzc
* @description 针对表【task_group】的数据库操作Mapper
* @createDate 2024-05-10 10:46:31
* @Entity tyut.selab.taskservice.domain.TaskGroup
*/
public interface TaskGroupDao {

    /**
     *  通过id删除任务对应小组
     * @param id
     * @return
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  对任务发布添加范围小组
     * @param records 小组对应信息列表
     * @return
     */

    Integer insert(List<TaskGroup> records);

    /**
     *  通过任务小组对应id查询指定对应信息
     * @param id
     * @return
     */

    TaskGroup selectById(Integer id);

    /**
     *  通过任务id查询所有对应小组
     * @return
     */
    List<TaskGroup> selectAllTaskGroupsByTaskId(Integer taskId);

    /**
     *  修改对应信息
     * @param record
     * @return
     */
    Integer updateById(TaskGroup record);

    /**
     *  通过groupId查询所有任务
     * @param groupId
     * @return
     */
    List<TaskGroup> selectByGroupId(Integer groupId);

}
