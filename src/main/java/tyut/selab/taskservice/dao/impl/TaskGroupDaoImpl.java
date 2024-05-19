package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskGroupDao;
import tyut.selab.taskservice.domain.TaskGroup;

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
        return null;
    }

}
