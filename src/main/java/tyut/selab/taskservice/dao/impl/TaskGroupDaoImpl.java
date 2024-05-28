package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskGroupDao;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
* @author lzc
* @description 针对表【task_group】的数据库操作Mapper
* @createDate 2024-05-10 10:46:31
* @Entity tyut.selab.taskservice.domain.TaskGroup
*/
public class TaskGroupDaoImpl extends BaseDao implements TaskGroupDao {

    /**
     *  通过id删除任务对应小组
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Integer id){
        String sql = "delete from task_group where id = ?";
        return baseUpdate(sql,id);
    }

    /**
     *  对任务发布添加范围小组 （测试ok)
     * @param records 小组对应信息列表
     * @return 返回 1 标识添加成功 返回0 标识添加失败
     */

    public Integer insert(List<TaskGroup> records){
        String sql = "insert into task_group(id,task_id,group_id) values(default,?,?)";
        int i = 0;
        for (TaskGroup taskGroup : records){
            baseUpdate(sql, taskGroup.getTaskId(), taskGroup.getGroupId());
            i ++ ;
        }
        if(i == records.size()){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     *  通过任务小组对应id查询指定对应信息
     * @param id
     * @return
     */

    public TaskGroup selectById(Integer id){
        return null;
    }

    /**
     * 通过任务id查询所有对应小组
     *
     * @return
     */
    public List<TaskGroup> selectAllTaskGroupsByTaskId(Integer taskId) {
        List<TaskGroup> list = null;
        String sql = """
                select id,task_id taskId,group_id groupId from task_group where task_id = ?
                """;
        return baseQuery(TaskGroup.class,sql,taskId);
    }

    /**
     *  修改对应信息
     * @param record
     * @return
     */
    public Integer updateById(TaskGroup record){
        return null;
    }

    /**
     *  通过groupId查询所有任务
     * @param groupId
     * @return
     */
    public List<TaskGroup> selectByGroupId(Integer groupId){
        //查询小组id对应的taskid
//        String sql="select * from task_group where group_id=?";
        String sql="select id,task_id taskId,group_id groupId from task_group where group_id=?";
        List<TaskGroup> taskGroups = baseQuery(TaskGroup.class, sql, groupId);
        return taskGroups;
    }

    @Override
    public List<String> findTaskGroupNamesByTaskId(Integer taskId) {

        //找到转换任务所对应的小组id
        List<TaskGroup> taskGroups = selectAllTaskGroupsByTaskId(taskId);
        List<String> groupNames = new ArrayList<>();

        //通过TaskGroup的list集合找到对应的小组名称集合
        if (taskGroups == null) {
            return groupNames;
        } else {
            String sql = """
                        select group_name groupName from sys_group where group_id = ?
                        """;
            for (TaskGroup taskGroup : taskGroups) {
                Group group = baseQueryObject(Group.class, sql, taskGroup.getGroupId());
                groupNames.add(group.getGroupName());
            }
            return groupNames;
        }
    }

    @Override
    public String findPublisherNameById(Integer publisherId) {
        String sql2 = """
                    select user_name userName from sys_user where user_id = ?
                    """;
        User user = baseQueryObject(User.class, sql2,publisherId);
        return user.getUserName();
    }

}
