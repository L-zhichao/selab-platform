package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.taskservice.view.TaskManagerVo;

import java.util.List;

public class TaskServiceImpl implements TaskInfoService {



    @Override
    public Integer save(TaskInfoDto taskInfoDto) {
        return null;
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
