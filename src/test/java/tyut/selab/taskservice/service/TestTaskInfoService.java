package tyut.selab.taskservice.service;

import org.junit.Test;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoVo;

/**
 * ClassName: TestTaskInfoService
 * Package: tyut.selab.taskservice.service
 * Description:
 *
 * @Author :triumph
 * @Create 2024/5/18 上午11:24
 * @Version 1.0
 */
public class TestTaskInfoService {
    TaskInfoService taskInfoService = new TaskServiceImpl();
    @Test
    public void test1(){
        TaskInfoVo taskInfoVo = taskInfoService.queryById(1);
        taskInfoVo.getGroupNames().forEach((groupName)->{
            System.out.println(groupName);
        });
    }
}
