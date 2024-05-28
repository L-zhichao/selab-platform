package tyut.selab.taskservice.service.impl;

import tyut.selab.taskservice.dao.BaseDao;
import tyut.selab.taskservice.dao.TaskGroupDao;
import tyut.selab.taskservice.dao.TaskInfoDao;
import tyut.selab.taskservice.dao.impl.TaskGroupDaoImpl;
import tyut.selab.taskservice.dao.impl.TaskInfoDaoImpl;
import tyut.selab.taskservice.domain.TaskGroup;
import tyut.selab.taskservice.domain.TaskInfo;
import tyut.selab.taskservice.dto.TaskInfoDto;
import tyut.selab.taskservice.service.TaskInfoService;
import tyut.selab.taskservice.view.TaskInfoVo;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.User;

import java.util.ArrayList;
import java.util.Date;
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
        TaskInfo tInfo = taskInfoDtoConvert(taskInfoDto);
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
    public Integer delete(Integer taskId) {//参数taskId就是taskInfo中的主键id

        //通过id判断任务是否存在
        TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskId);

        if(taskInfo!=null){//任务存在
//            try {
//                taskInfo.setDelFlag(0);
//                //执行删除
//                Integer i = taskInfoDao.deleteByPrimaryKey(taskId);
//                taskInfo.setDelFlag(1);//更改删除状态
//                return i;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            //将taskInfo表中的del——flag改为1
            taskInfoDao.deleteByPrimaryKey(taskId);
            //删除taskGroup中的任务和小组的对应关系
            List<TaskGroup> list = taskGroupDao.selectAllTaskGroupsByTaskId(taskId);
            for (TaskGroup taskGroup : list){
                taskGroupDao.deleteByPrimaryKey(taskGroup.getId());
            }
            return 1;
        }
        return 0;
    }

    @Override
    public Integer update(TaskInfoDto taskInfoDto,Integer taskId) {
        //判断修改后的任务在数据库中是否和其他的任务信息冲突
        int isConflict = taskInfoDao.conflict(taskInfoDto, taskId);

        if (isConflict == 1) {
            return 1;
        }else {
            //判断任务是否存在
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskId);

            if (taskInfo != null) {//标识存在任务对象
                //将数据封装进入 TaskInfo 对象
                TaskInfo tInfo = taskInfoDtoConvert(taskInfoDto);
                tInfo.setId(taskId);

                //删除上次保存的任务和小组对应关系
                List<TaskGroup> pastList = taskGroupDao.selectAllTaskGroupsByTaskId(taskId);
                for (TaskGroup pastTaskGroup : pastList) {
                    taskGroupDao.deleteByPrimaryKey(pastTaskGroup.getId());
                }
                //重新存入任务和小组的对应关系
                List<TaskGroup> newList = new ArrayList<>();
                for (Integer groupId : taskInfoDto.getGroupIds()) {
                    TaskGroup taskGroup = new TaskGroup(null, taskId, groupId);
                    newList.add(taskGroup);
                }
                taskGroupDao.insert(newList);
                //调用相关方法，修改数据库
                taskInfoDao.updateBytaskId(tInfo);
                return 2;
            } else {
                return 0;
            }
        }

    }

    @Override
    public TaskInfoVo queryById(Integer taskId) {
        TaskInfoVo taskInfoVo = null;
        //查询task_info表内容数据
        TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskId);
        //如果查询到任务，那么就封装成taskinfovo对象
        if (taskInfo != null) {
            taskInfoVo = taskInfoConvert(taskInfo);
            //将所有的数据封装成taskinfovo返回
        }
        if (taskInfoVo == null){
            return null;
        }
        return taskInfoVo;
    }

    @Override
    public List<TaskInfoVo> queryAllTask() {
        //在数据库中查询所有的，没有被删除的任务
        List<TaskInfo> taskInfos = taskInfoDao.selectAllTaskInfo();
        List<TaskInfoVo> taskInfoVos = new ArrayList<>();

        if(taskInfos != null) {
            //有任务的话就封装，没有任务的话返回空集合
            for (TaskInfo taskInfo : taskInfos) {
                TaskInfoVo taskInfoVo = taskInfoConvert(taskInfo);
                if (taskInfoVo == null){
                    continue;
                }
                taskInfoVos.add(taskInfoVo);
            }
        }
        //将每一个任务尽心封装成taskinfovo类型的对象，添加进list集合中
        return taskInfoVos;
    }

    @Override
    public List<TaskInfoVo> queryTaskInfoByUserId(Integer userId) {
        return null;
    }

    @Override
    public List<TaskInfoVo> queryTaskInfoBypublish(String userName) {
        List<TaskInfoVo> taskInfoVos = queryAllTask();
        List<TaskInfoVo> taskInfoVosOfPublisher = new ArrayList<>();
        for (TaskInfoVo taskInfoVo : taskInfoVos) {
            if (taskInfoVo.getPublisherName().equals(userName)) {
                taskInfoVosOfPublisher.add(taskInfoVo);
            }
        }
        return taskInfoVosOfPublisher;
    }

    @Override
    public List<TaskInfoVo> queryTaskInfoByGroupId(Integer groupId) {
        //查询groupTask表格，查询groupId对应的所有任务id
        List<TaskGroup> list = taskGroupDao.selectByGroupId(groupId);
        //查询TaskInfo表格，将所有的任务id相对的任务进行返回
            //在taskgroup表格中，有很多的关系已经被废弃，需要删除
        //将所有的任务封装返回
        List<TaskInfoVo> taskInfoVos = new ArrayList<>();
        for (TaskGroup taskGroup : list) {
            TaskInfo taskInfo = taskInfoDao.selectByTaskId(taskGroup.getTaskId());
            TaskInfoVo taskInfoVo = taskInfoConvert(taskInfo);
            if (taskInfoVo == null){
                continue;
            }
            taskInfoVos.add(taskInfoVo);
        }
        return taskInfoVos;
    }


    /**
     * 实现类内部的方法，将数据库中查询到的TaskInfo对象封装为TaskInfoVo传给前端
     * @param taskInfo
     * @return 正常返回taskinfovo对象，如果返回null标识异常
     */
    private TaskInfoVo taskInfoConvert(TaskInfo taskInfo) {

            TaskInfoVo taskInfoVo = new TaskInfoVo();

            taskInfoVo.setId(taskInfo.getId());
            taskInfoVo.setName(taskInfo.getName());
            taskInfoVo.setContent(taskInfo.getContent());
            taskInfoVo.setDealTime(taskInfo.getDealTime());
            taskInfoVo.setPublishTime(taskInfo.getPublishTime());

            //将查到的数据和task_group，sys_group进行查询，找到任务所包含的小组名称
            List<String> GroupNames = taskGroupDao.findTaskGroupNamesByTaskId(taskInfoVo.getId());
            taskInfoVo.setGroupNames(GroupNames);


            //将查到的数据和sys_user进行查询，找到publisher名字
            String publisherName = taskGroupDao.findPublisherNameById(taskInfo.getPublisherId());
            if (publisherName == null){
                delete(taskInfo.getId());
                return null;
            }
            taskInfoVo.setPublisherName(publisherName);

        //将任务截至时间和当前时间进行比对，判断是否结束
            Date date = new Date();
            boolean flag = date.getTime() > taskInfoVo.getDealTime().getTime();
            if (flag) {
                taskInfoVo.setStatus(1);
            } else {
                taskInfoVo.setStatus(0);
            }
        return taskInfoVo;
    }

    /**
     *  接收前端传来的TaskInfoDto对象，并且转换位后端数据库中的taskInfo对象
     * @param taskInfoDto
     * @return
     */
    private TaskInfo taskInfoDtoConvert(TaskInfoDto taskInfoDto) {
        //将数据封装进TaskInfo对象
        TaskInfo tInfo = new TaskInfo();
        tInfo.setId(null);
        tInfo.setPublisherId(taskInfoDto.getPublisherId());
        tInfo.setUpdaterId(taskInfoDto.getUpdaterId());
        tInfo.setName(taskInfoDto.getName());
        tInfo.setContent(taskInfoDto.getContent());
        tInfo.setDealTime(taskInfoDto.getDealTime());
        return tInfo ;
    }
}
