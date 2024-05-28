package tyut.selab.taskservice.dao;


import org.junit.jupiter.api.Test;
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

    private TaskGroupDao taskGroupDao = new TaskGroupDaoImpl();

    @Test
    public void testInsert(){
        TaskGroup TaskGroup1 = new TaskGroup(null,1,1);
        TaskGroup TaskGroup2 = new TaskGroup(null,1,2);
        TaskGroup TaskGroup3 = new TaskGroup(null,1,3);
        List list = new ArrayList();
        list.add(TaskGroup1);
        list.add(TaskGroup2);
        list.add(TaskGroup3);
        taskGroupDao.insert(list);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        taskGroupDao.deleteByPrimaryKey(72);
    }

    @Test
    public void testSelectAllTaskGroupsByTaskId() {
        List<TaskGroup> list = taskGroupDao.selectAllTaskGroupsByTaskId(1);
        for (TaskGroup taskGroup : list){
            System.out.println(taskGroup.getGroupId());
        }
    }

    @Test
    public void testSelectByGroupId() {
        List<TaskGroup> list = taskGroupDao.selectByGroupId(2);
        for (TaskGroup taskGroup : list){
            System.out.println(taskGroup.getId());
        }
    }

    @Test
    public void testFindTaskGroupNamesByTaskId() {
        List<String> taskGroupNamesByTaskId = taskGroupDao.findTaskGroupNamesByTaskId(1);
        System.out.println("*************");
        for (String string : taskGroupNamesByTaskId){
            System.out.println(string);
        }
    }

    @Test
    public void testFindPublisherNameById() {
        System.out.println(taskGroupDao.findPublisherNameById(1));
    }


}
