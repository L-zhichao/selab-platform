package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.DaoImpl.GroupDaoImpl;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.Page;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.JudgeRoleId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao = new GroupDaoImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Integer insert(GroupDto groupDto) {

        Group group = new Group();
        group.setGroupName(groupDto.getGroupName());
//        Integer roleId = JudgeRoleId.GetJudgeRoleId();
//        if (roleId.equals(1) || roleId.equals(2)) {
//
//        }
//        SecurityUtil securityUtil = new SecurityUtil();
//        UserLocal user = securityUtil.getUser();
//        Integer userId = user.getUserId();
//        Integer groupId = user.getGroupId();
//        group.setParentId(userId);
//        group.setUpdateUser(groupId);
        group.setParentId(1);
        group.setUpdateUser(01);
        return groupDao.insert(group);

       // return 0;
    }

    @Override
    public Integer delete(Integer groupId) {
        return groupDao.delete(groupId);
//        Integer roleId = JudgeRoleId.GetJudgeRoleId();
//        if (roleId.equals(1) || roleId.equals(2)) {
//
//        }
//        return 0;
    }

    @Override
    public Page<GroupVo> selectAllGroup(Integer cur, Integer szie) {
        Page<GroupVo> page = new Page<>();
        List<GroupVo> list = new ArrayList<>();
        List<Group> groups = groupDao.selectAllGroup(cur, szie);
        Integer groupTotal = groupDao.getGroupTotal();
        for (Group group : groups) {
            GroupVo groupVo = new GroupVo();
            Integer groupId = group.getGroupId();
            String groupName = group.getGroupName();
            Date createTime = group.getCreateTime();
            Page<UserVo> pageuserVos = userService.selectByGroupId(groupId,1,5);
            groupVo.setGroupId(groupId);
            groupVo.setGroupName(groupName);
            groupVo.setCreateTime(createTime);
            groupVo.setUserVos(pageuserVos.getData());
            list.add(groupVo);
        }
        page.setCur(cur);
        page.setSize(szie);
        page.setTotal(groupTotal);
        page.setData(list);

        return page;
//        Integer roleId = JudgeRoleId.GetJudgeRoleId();
//        if (roleId.equals(1) || roleId.equals(2)) {
//
//        }
//        return null;
    }

    @Override
    public Integer update(GroupVo groupVo) {
        Group group = new Group();
        group.setGroupName(groupVo.getGroupName());
        group.setCreateTime(groupVo.getCreateTime());
        group.setGroupId(groupVo.getGroupId());
        return groupDao.update(group);

//        Integer roleId = JudgeRoleId.GetJudgeRoleId();
//        if (roleId.equals(1) || roleId.equals(2)) {
//
//        }
//        return 0;
    }

    @Override
    public List<Integer> selectAllGroupId() {
        return groupDao.selectAllGroupId();
    }


}
