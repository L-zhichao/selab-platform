package tyut.selab.taskservice.dao.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.domain.TaskInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* @author 26580
* @description 针对表【task_info】的数据库操作Mapper
* @createDate 2024-05-10 10:44:51
* @Entity tyut.selab.taskservice.domain.TaskInfo
*/
public class TaskInfoDaoImpl  extends BaseDao implements TaskInfoDao {


    /**
     *  删除任务信息
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(Long id){
        return null;
    }

    /**
     *  增加任务信息.返回的正数标识添加成功，正数是任务的id，0 标识添加失败
     *  任务的name和content 和数据库中没有删除的任务 不能相同，否则返回0,标识任务已存在
     * @param record
     * @return
     */
    public Integer insert(TaskInfo record){
        String sql3 = "select id from task_info where name = ? and content = ?  and del_flag = 0 ";
        TaskInfo taskInfo2 = baseQueryObject(TaskInfo.class, sql3, record.getName(), record.getContent());
        if (taskInfo2 != null){
            return 0;
        }else{
            String sql1 = """
                insert into task_info
                values (default,?,?,?,?,?,now(),now(),0)
                """;
            Timestamp timestamp = new Timestamp(record.getDealTime().getTime());
            baseUpdate(sql1,record.getPublisherId(),record.getUpdaterId(),record.getName(),record.getContent(),timestamp);
            String sql2 = "select id from task_info where name = ? and content = ? and del_flag = 0";
            TaskInfo taskInfo1 = baseQueryObject(TaskInfo.class, sql2, record.getName(), record.getContent());
            return taskInfo1.getId();
        }
    }

    /**
     *  通过taskId查询任务信息
     * @param id
     * @return
     */
    public TaskInfo selectByTaskId(Integer id){
        TaskInfo taskInfo = null;
        String sql = """
                select id,publisher_id publisherId,updater_id updaterId,name,content,deal_time dealTime,publish_time publishTime,update_time updateTime
                from task_info
                where id = ? and del_flag = 0
                """;
        taskInfo = baseQueryObject(TaskInfo.class,sql,id);
        return taskInfo;
    }

    /**
     *  修改任务信息
     * @param record
     * @return
     */
    public Integer updateBytaskId(TaskInfo record){
        String sql =
                """
                update task_info set updater_id = ?,name = ?,content=?,deal_time = ?,update_time = now() where id = ?
                """;
        return baseUpdate(sql,record.getUpdaterId(), record.getName(),record.getContent(),record.getDealTime(),record.getId());
    }

    /**
     *  查询所有任务信息
     * @return
     */
    public List<TaskInfo> selectAllTaskInfo(){
        String sql = """
                select id,publisher_id publisherId,updater_id updaterId,name,content,deal_time dealTime,publish_time publishTime,update_time updateTime
                from task_info
                where del_flag = 0
                """;
        List<TaskInfo> taskInfos = baseQuery(TaskInfo.class, sql);
        return taskInfos;
    }

    /**
     *  查询未到截止时间的任务信息
     * @return
     */
    public List<TaskInfo> selectByDealTimeTaskInfos(){
        return null;
    }

}
