package tyut.selab.taskservice.dao;

import com.alibaba.druid.support.json.JSONUtils;
import org.junit.Test;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.domain.TaskInfo;

import java.sql.Timestamp;
import java.util.Date;

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
}



