package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.DaoImpl.GroupDaoImpl;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;



public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao = new GroupDaoImpl();
    @Override
    public Integer insert(GroupDto groupDto) throws Exception {

        Group group = new Group();
        group.setGroupName(groupDto.getGroupName());
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());

        //目前不清楚咋搞
        group.setParentId(1);
        group.setUpdateUser(01);
        return groupDao.insert(group);
    }

    @Override
    public Integer delete(Integer groupId) {

        return null;
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
