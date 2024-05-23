package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.DaoImpl.GroupDaoImpl;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.vo.GroupVo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao = new GroupDaoImpl();
    @Override
    public Integer insert(GroupDto groupDto)  {

        Group group = new Group();
        group.setGroupName(groupDto.getGroupName());
        //等代码实现
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
    public Integer delete(GroupDto groupDto)  {
        return groupDao.delete(groupDto);
    }

    @Override
    public List<GroupVo> selectAllGroup() {

        return null;
    }

    @Override
    public Integer update(GroupVo groupVo) {

        return null;
    }


}
