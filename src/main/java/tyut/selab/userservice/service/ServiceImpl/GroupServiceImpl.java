package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.service.GroupService;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.userservice.vo.UserVo;

import java.util.List;



public class GroupServiceImpl implements GroupService {
    private GroupDao groupDao;
    @Override
    public Integer insert(GroupDto groupDto)  {

        Group group = null;
        group.setGroupName(groupDto.getGroupName());
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

    public static class UserServiceImpl implements UserService {
        @Override
        public Integer updateUserRole(UserVo userVo) {
            return null;
        }

        @Override
        public List<UserVo> selectByGroupId(Integer groupId) {
            return null;
        }

        @Override
        public UserVo selectByUserId(Integer userId) {
            return null;
        }

        @Override
        public Integer delete(Integer userId) {
            return null;
        }

        @Override
        public Integer save(UserVo userVo) {
            return null;
        }
    }
}
