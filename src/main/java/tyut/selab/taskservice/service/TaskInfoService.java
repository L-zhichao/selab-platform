package tyut.selab.taskservice.service;

import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.view.TaskInfoVo;

import java.util.List;

/**
 *
 */
public interface TaskInfoService {

    /**
     * 增加任务信息
     * @return
     */
    public Integer save(TaskInfoDto taskInfoDto);

    /**
     *
     * @param taskInfoDto
     * @return
     */
    public Integer update(TaskInfoDto taskInfoDto);

    /**
     *  查询所有任务
     * @return
     */
    public List<TaskInfoVo> queryAllTask();

    /**
     *  查询用户收到的任务信息
     * @return
     */
    public List<TaskInfoVo> queryTaskInfoByUserId(Integer userId);

    /**
     *  通过用户名称查询用户发布的所有任务
     * @param userName
     * @return
     */
    public List<TaskInfoVo> queryTaskInfoBypublish(String userName);


}
