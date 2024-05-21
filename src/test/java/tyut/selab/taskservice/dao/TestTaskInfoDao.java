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
    public void test1() {
        TaskInfo taskInfo = new TaskInfo(1, 1, "test1", "test1 content", new Date());
        taskInfoDao.insert(taskInfo);
    }

    @Test
    public void test2() {
        TaskInfo taskInfo = new TaskInfo(1, 1, "test2", "test2 content", new Date());
        int taskid = taskInfoDao.insert(taskInfo);
        System.out.println(taskid);
    }

    @Test
    public void test3() {
        TaskInfo taskInfo = new TaskInfo(1, 1, "test3", "test3 content", new Date());
        int taskid = taskInfoDao.insert(taskInfo);
        System.out.println(taskid);
    }

    @Test
    public void test4(){
//        String json = """
//                {
//                "name": "zhangsan",
//                }
//                """;
//        person person = new person();
//        person.name = "lisi";
//
//        System.out.println(JSON.parseObject(json, person.class));
//        System.out.println(JSON.toJSONString(person));
        List<Integer> groupIds = new ArrayList<Integer>();
        groupIds.add(1);
        groupIds.add(2);
        groupIds.add(3);
        groupIds.add(4);
        Date date =new Date();
        TaskInfoDto taskInfoDto = new TaskInfoDto(1,1,"test1","test1",groupIds,date);
        TaskInfoDto taskInfoDto1 = JSON.parseObject(JSON.toJSONString(taskInfoDto), TaskInfoDto.class);
        System.out.println(taskInfoDto1);
        System.out.println(JSON.toJSONString(taskInfoDto));
    }

    @Test
    public void test5(){
        //日期类型转换为json后是时间戳
        Date date = new Date();
        System.out.println(JSONObject.toJSONString(date));
        System.out.println(date.getTime());
        System.out.println(date);
    }

}




