package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.DaoImpl.GroupDaoImpl;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;




public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao = new GroupDaoImpl();
    private UserService userService = new UserServiceImpl();
    @Override
    public Integer insert(GroupDto groupDto)  {

        Group group = new Group();
        group.setGroupName(groupDto.getGroupName());
        //等代码实现,别删下面这段
//        SecurityUtil securityUtil = new SecurityUtil();
//        UserLocal user = securityUtil.getUser();
//        Integer userId = user.getUserId();
//        Integer groupId = user.getGroupId();
//        group.setParentId(userId);
//        group.setUpdateUser(groupId);
        //先这么处理
        group.setParentId(1);
        group.setUpdateUser(01);
        return groupDao.insert(group);
    }

    @Override
    public Integer delete(Integer groupId)  {
        return groupDao.delete(groupId);
    }

    @Override
    public List<GroupVo> selectAllGroup(Integer cur,Integer szie) {
        List<GroupVo> list = new ArrayList<>();
        List<Group> groups = groupDao.selectAllGroup(cur,szie);
        for (Group group:groups) {
            GroupVo groupVo =new GroupVo();
            Integer groupId = group.getGroupId();
            String groupName = group.getGroupName();
            Date createTime = group.getCreateTime();
            List<UserVo> userVos = userService.selectByGroupId(groupId);
            groupVo.setGroupId(groupId);
            groupVo.setGroupName(groupName);
            groupVo.setCreateTime(createTime);
            groupVo.setUserVos(userVos);
            list.add(groupVo);
        }
        return list;
    }

    @Override
    public Integer update(GroupVo groupVo) {
        Group group = new Group();
        group.setGroupName(groupVo.getGroupName());
        group.setCreateTime(groupVo.getCreateTime());
        group.setGroupId(groupVo.getGroupId());
        return groupDao.update(group);
    }


}
