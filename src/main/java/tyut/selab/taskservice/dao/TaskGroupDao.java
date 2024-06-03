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
     * @return 返回null标识此任务没有分配小组
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

    /**
     * 根据任务和小组对应的关系找到任务ID所对应的所有小组名称
     * @param taskId
     * @return 返回小组名称的list集合,如果没有小组，则返回空集合
     */
    List<String> findTaskGroupNamesByTaskId(Integer taskId);

    /**
     *  根据发布者名称查询发布者的名称
     * @param publisherId
     * @return 返回发布者的名称
     */
    String findPublisherNameById(Integer publisherId);

    /**
     *     查询集合中的group是否存在，返回Trun 表示存在
     * @param groupIds
     * @return
     */
    boolean isGroupsExist(List<Integer> groupIds);
}
