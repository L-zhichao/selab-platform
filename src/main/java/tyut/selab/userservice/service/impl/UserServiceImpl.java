package tyut.selab.userservice.service.impl;

import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.dao.impl.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public Integer updateUserRole(UserVo userVo) {
        //Date date = new Date();
        User user = new User();

        Long userId = userVo.getUserId();
        Integer roleId = userVo.getRoleId();
        user.setUserId(userId);
        user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setRoleId(roleId);
        user.setDelFlag(0);
        Integer updated = userDao.updateUserRole(user);
        return updated;
    }

    @Override
    public List<UserVo> selectByGroupId(Integer groupId) {
        ArrayList<UserVo> userVos = new ArrayList<>();

        return null;
    }


    @Override
    public Integer update(UserVo userVo) {

            User user = new User();
            Long userId = userVo.getUserId();
            String userName = userVo.getUserName();
            Integer groupId = userVo.getGroupId();
            Integer roleId = userVo.getRoleId();
            String email = userVo.getEmail();
            String phone = userVo.getPhone();
            Integer sex = userVo.getSex();
            user.setUserId(userId);
            user.setUserName(userName);
            user.setGroupId(groupId);
            user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
            user.setRoleId(roleId);
            user.setEmail(email);
            user.setPhone(phone);
            user.setSex(sex);
            user.setDelFlag(0);
            Integer updated = userDao.updateUser(user);
            return updated;



    }

    @Override
    public Integer groupUpdate(UserVo userVo) {
        User user = new User();
        Long userId = userVo.getUserId();
        Integer groupId = userVo.getGroupId();
        user.setUserId(userId);
        user.setGroupId(groupId);
        Integer update=userDao.groupUpdate(user);
        return update;
    }

    @Override
    public UserVo selectByUserId(Long userId) {
        UserVo userVo=new UserVo();
        User user = userDao.selectByUserIdUser(userId);
        Long userid = user.getUserId();
        String username = user.getUserName();
        //根据userId查询groupId
        Integer groupId = user.getGroupId();
        Integer roleId = user.getRoleId();
        //根据groupId查询groupName返回
        String groupName=userDao.groupName(groupId);
        //根据roleId查询roleName返回//无
        String email = user.getEmail();
        String phone = user.getPhone();
        Integer sex = user.getSex();

        if(roleId == 1){
            userVo.setRoleName("超级管理员");
        } else if (roleId == 2) {
            userVo.setRoleName("管理员");

        }else if(roleId==3){
            userVo.setRoleName("用户");
        }/*else {
            throw new RuntimeException("找不到该用户类别");
        }*/
        userVo.setUserName(username);
        userVo.setGroupId(groupId);
      userVo.setGroupName(groupName);
        userVo.setRoleId(roleId);
//      userVo.setRoleName();nonono
        userVo.setEmail(email);
        userVo.setPhone(phone);
        userVo.setSex(sex);
        userVo.setUserId(userid);
        userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        return userVo;
    }

    @Override
    public Integer delete(Integer userId) {
        UserLogoutDao userLogoutDao = new UserLogoutDao();
        UserLogout userLogout = new UserLogout();
        userLogout.setUserId(userId);
        userLogout.setAdminId(2);
        userLogout.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        Integer deleteByUserId = userDao.deleteByUserId(userId);
        if (deleteByUserId>0){
            userLogoutDao.insert(userLogout);
        }
        return deleteByUserId;
    }

    @Override
    public Integer save(UserVo userVo) {
        //Date date = new Date();
        User user = new User();
        String userName = userVo.getUserName();
        Integer groupId = userVo.getGroupId();
        Integer roleId = userVo.getRoleId();
        String email = userVo.getEmail();
        String phone = userVo.getPhone();
        Integer sex = userVo.getSex();
        String password = userVo.getPassword();
        user.setUserName(userName);
        user.setGroupId(groupId);
        user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setRoleId(roleId);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setDelFlag(0);
        user.setPassword(password);
        Integer updated = userDao.insertUser(user);
        return updated;
    }
}
