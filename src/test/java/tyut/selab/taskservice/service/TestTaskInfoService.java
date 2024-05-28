package tyut.selab.taskservice.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.service.impl.TaskServiceImpl;
import tyut.selab.taskservice.view.TaskInfoVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void testSave() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        TaskInfoDto taskInfoDto = new TaskInfoDto(3, 3, "testPublisherService1", "testService1 content",list,new Date());
        Integer save = taskInfoService.save(taskInfoDto);
        System.out.println(save);
    }

    @Test
    public void testUpdate() {
        //更新任务信息
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);

        TaskInfoDto taskInfoDto = new TaskInfoDto(1, 1, "updateService1", "updateService1 content",list,new Date());
        Integer update = taskInfoService.update(taskInfoDto, 14);
        System.out.println(update);

    }
    
    @Test
    public void testQueryById(){
        TaskInfoVo taskInfoVo = taskInfoService.queryById(16);
        System.out.println(taskInfoVo);
    }

    @Test
    public void testQueryAllTask() {
        List<TaskInfoVo> taskInfoVos = taskInfoService.queryAllTask();
        for (TaskInfoVo taskInfoVo : taskInfoVos){
            System.out.println(taskInfoVo);
        }
    }

    @Test
    public void testQueryTaskInfoBypublish() {
        List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoBypublish("lisi");
        taskInfoVos.forEach(System.out::println);
    }

    @Test
    public void testQueryTaskInfoByGroupId() {
        List<TaskInfoVo> taskInfoVos = taskInfoService.queryTaskInfoByGroupId(1);
        taskInfoVos.forEach(System.out::println);
    }


    @Test
    public void test2(){
        //创建信息
        TaskInfo taskInfo1 = new TaskInfo(1, 1, "test1", "test1 content", new Date());
        TaskInfo taskInfo2 = new TaskInfo(1, 1, "test2", "test2 content", new Date());



    }
}
