package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

import java.sql.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    //没有IOC
    private UserDaoImpl userDao;


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

        User user = userDao.selectByUserIdUser(userId);

        UserVo userVo = new UserVo();

        Long userid = user.getUserId();
        String username = user.getUserName();
        Integer roleld = user.getRoleId();
        //rolename在业务层
        //组id在group表里，组名在业务层加
        String email = user.getEmail();
        String phone = user.getPhone();
        Integer sex = user.getSex();
        Date createTime = (Date) user.getCreateTime();
        Date updateTime = (Date) user.getUpdateTime();

        //对rolename的判断
        if(roleld == 1){

            userVo.setRoleName("超级管理员");
        } else if (roleld == 2) {
            userVo.setRoleName("管理员");

        }else{
            userVo.setRoleName("用户");
        }


        userVo.setUserName(username);
//        userVo.setGroupId();
//        userVo.setGroupName();
        userVo.setRoleId(roleld);
        userVo.setEmail(email);
        userVo.setPhone(phone);
        userVo.setSex(sex);
        userVo.setUserId(userid);
        userVo.setCreateTime(createTime);
        userVo.setUpdateTime(updateTime);

        return userVo;
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
