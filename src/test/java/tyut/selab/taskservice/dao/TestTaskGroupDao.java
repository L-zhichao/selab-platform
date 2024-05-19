package tyut.selab.taskservice.dao;

import org.junit.Test;
import tyut.selab.taskservice.dao.impl.TaskGroupDaoImpl;
import tyut.selab.taskservice.domain.TaskGroup;

import java.util.ArrayList;
import java.util.List;



/**
 * ClassName: TestTaskGroupDao
 * Package: tyut.selab.taskservice.dao
 * Description:
 *
 * @Author :triumph
 * @Create 2024/5/16 下午8:16
 * @Version 1.0
 */
public class TestTaskGroupDao {

    @Test
    public void test1(){
        TaskGroupDao taskGroupDao = new TaskGroupDaoImpl();
        TaskGroup TaskGroup1 = new TaskGroup(null,1,2);
        TaskGroup TaskGroup2 = new TaskGroup(null,1,2);
        TaskGroup TaskGroup3 = new TaskGroup(null,1,2);
        List list = new ArrayList();
        list.add(TaskGroup1);
        list.add(TaskGroup2);
        list.add(TaskGroup3);
        taskGroupDao.insert(list);
    }



}
