package tyut.selab.userservice.service.impl;

import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.GroupDaoImpl;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.Result;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao=new GroupDaoImpl();
    private UserService userService= new UserServiceImpl();
    @Override
    public Integer insert(GroupDto groupDto) {
        String groupName = groupDto.getGroupName();
        Group group = new Group();
        group.setGroupName(groupName);
        group.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        group.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        group.setParentId(1);
        group.setUpdateUser(1);
        Integer insert = groupDao.insert(group);
        return insert;
    }

    @Override
    public Integer delete(Integer groupId) {
        Integer delete = groupDao.delete(groupId);
        return delete;
    }

    @Override
    public List<GroupVo> selectAllGroup(Integer cur,Integer szie) {
        List<Group> groups = groupDao.selectAllGroup(cur,szie);
        ArrayList<GroupVo> groupVos = new ArrayList<>();
        for (Group group:groups){
            GroupVo groupVo = new GroupVo();
            Integer groupId = group.getGroupId();
            String groupName = group.getGroupName();
            Date createTime = group.getCreateTime();
            Date updateTime = group.getUpdateTime();
            //通过groupid查询所有的uservo对象
            List<UserVo> userVos = userService.selectByGroupId(groupId);
            groupVo.setUserVos(userVos);
            groupVo.setGroupId(groupId);
            groupVo.setGroupName(groupName);
            groupVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
            //groupVo.setUserVos(userVos);
            groupVos.add(groupVo);
        }
        return groupVos;
    }

    @Override
    public Integer update(GroupVo groupVo) {
        Group group = new Group();
        Integer groupId = groupVo.getGroupId();
        String groupName = groupVo.getGroupName();
        //group.setCreateTime(groupVo.getCreateTime());
        //更改时间ing
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setUpdateUser(1);
        //group.setCreateTime(groupVo.getCreateTime());
        group.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        group.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        Integer update = groupDao.update(group);
        return update;
    }
}
