package tyut.selab.taskservice.dao;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.dto.TaskInfoDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: TestTaskInfoDao
 * Package: tyut.selab.taskservice.dao
 * Description:
 *
 * @Author :triumph
 * @Create 2024/5/16 下午9:06
 * @Version 1.0
 */
public class TestTaskInfoDao {
    TaskInfoDao taskInfoDao = new TaskInfoDaoImpl();

    @Test
    public void testInsert() {
        TaskInfo taskInfo = new TaskInfo(1, 1, "test2", "test2 content", new Date());
//        TaskInfo taskInfo = new TaskInfo(1, 1, "test1", "test1 content", new Date());
        Integer insert = taskInfoDao.insert(taskInfo);
        System.out.println(insert);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int taskid = taskInfoDao.deleteByPrimaryKey(13);
        System.out.println(taskid);
    }

    @Test
    public void testSelectByTaskId() {
        TaskInfo taskInfo = taskInfoDao.selectByTaskId(14);
        System.out.println(taskInfo.getName());
        System.out.println(taskInfo.getContent());
    }

    @Test
    public void testUpdateBytaskId(){
        TaskInfo taskInfo = new TaskInfo(1, 1, "update 1", "update 1 content", new Date());
        taskInfo.setId(15);
        Integer i = taskInfoDao.updateBytaskId(taskInfo);
        System.out.println(i);
    }

    @Test
    public void testSelectAllTaskInfo(){
        List<TaskInfo> taskInfos = taskInfoDao.selectAllTaskInfo();
        for (TaskInfo taskInfo : taskInfos){
            System.out.println(taskInfo.getContent());
        }
    }

    @Test
    public void testConflict() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        TaskInfoDto taskInfoDto = new TaskInfoDto(1, 1, "test1", "test1 content",list,new Date());
        int conflict = taskInfoDao.conflict(taskInfoDto, 15);
        System.out.println(conflict);
    }



}




