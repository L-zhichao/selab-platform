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
     * 增加任务信息 (返回数字标识为任务的id，返回0 标识任务标题和内容都重复)
     * @return
     */
    public Integer save(TaskInfoDto taskInfoDto);

    /**
     * 更新任务信息
     * @param taskInfoDto
     * @return 返回0 标识任务不存在,返回1 标识修改的任务和现有的任务冲突，返回数字标识任务修改完成
     */
    public Integer update(TaskInfoDto taskInfoDto,Integer taskId);

    /**
     * 通过 taskId查询对应的任务信息
     * @param taskId
     * @return 返回null标识任务不存在
     */
    public TaskInfoVo queryById(Integer taskId);

    /**
     *  查询所有任务
     * @return 返回null 标识当前没有任务
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
