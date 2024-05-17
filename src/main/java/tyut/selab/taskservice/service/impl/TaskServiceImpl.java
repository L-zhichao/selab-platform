package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dao.TaskGroupDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.dao.impl.TaskGroupDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.view.TaskInfoVo;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskInfoService {

    /**
     * 用于调用 taskinfo的Dao层相关方法
     */
    private TaskInfoDao taskInfoDao = new TaskInfoDaoImpl();
    /**
     * 用于调用 TaskGroup的Dao层相关方法
     */
    private TaskGroupDao taskGroupDao = new TaskGroupDaoImpl();


    @Override
    public Integer save(TaskInfoDto taskInfoDto) {
        //将数据封装进TaskInfo对象
        TaskInfo tInfo = new TaskInfo();
        tInfo.setId(null);
        tInfo.setPublisherId(taskInfoDto.getPublisherId());
        tInfo.setUpdaterId(taskInfoDto.getUpdaterId());
        tInfo.setName(taskInfoDto.getName());
        tInfo.setContent(taskInfoDto.getContent());
        tInfo.setDealTime(taskInfoDto.getDealTime());
        Integer taskId = taskInfoDao.insert(tInfo);
        //将数据封装进TaskGroup对象列表(自增id，任务id，小组id）
            //先获取任务的id
        //遍历数组，将数据封装进集合
        //调用dao层方法，将数据放入数据库
        //Integer insert(List<TaskGroup> records);grouptask表
        //Integer insert(TaskInfo record);taskinfo表
        if (taskId > 0) {
            List<TaskGroup> list = new ArrayList<>();
            for (Integer groupId : taskInfoDto.getGroupIds()) {
                TaskGroup taskGroup = new TaskGroup(null,taskId,groupId);
                list.add(taskGroup);
            }
            taskGroupDao.insert(list);
            return taskId;
        }else{
            return 0;
        }



    }

    @Override
    public Integer update(TaskInfoDto taskInfoDto) {
        return null;
    }

    @Override
    public List<TaskInfoVo> queryAllTask() {
        return null;
    }

    @Override
    public List<TaskInfoVo> queryTaskInfoByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<TaskInfoVo> queryTaskInfoBypublish(String userName) {
        return null;
    }
}
