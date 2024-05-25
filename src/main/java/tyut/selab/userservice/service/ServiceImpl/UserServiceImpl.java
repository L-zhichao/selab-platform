package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

import java.sql.Date;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private UserLogoutDao userLogoutDao = new UserLogoutDaoImpl();

    /**
    * Description: 修改用户角色
    * @param userVo
    * @return Integer
    */
    @Override
    public Integer updateUserRole(UserVo userVo) {
        User user = new User();
        user.setUserId(userVo.getUserId());
        user.setUserId(userVo.getUserId());
        //user为Date,sql为DateTime
        user.setUpdateTime(Date.valueOf(String.valueOf(LocalDateTime.now())));

        //调用sql方法
        return userDao.updateUserRole(user);

    }

    @Override
    public List<UserVo> selectByGroupId(Integer groupId) {
        return null;
    }

    @Override
    public UserVo selectByUserId(Long userId) {
        System.out.println("doSelectByUserIdService");
        User userSelectByUserId = userDao.selectByUserIdUser(userId);

        UserVo userVo = new UserVo();

        Long userid = (userSelectByUserId.getUserId());
        String username = userSelectByUserId.getUserName();
        Integer roleld = userSelectByUserId.getRoleId();
        //rolename在业务层
        //组id在user-group表里，组名在业务层加
        String email = userSelectByUserId.getEmail();
        String phone = userSelectByUserId.getPhone();
        Integer sex = userSelectByUserId.getSex();


        java.util.Date date = new java.util.Date();
        Date createTime = new java.sql.Date(date.getTime());
        Date updateTime = new java.sql.Date(date.getTime());

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

    /**
    * Description: 注销用户
    * @param userId
    * @return Integer
    */
    @Override
    public Integer delete(Integer userId) {
        //封装Userlogout数据
        UserLogout userLogout = new UserLogout();
        userLogout.setUserId(userId);
        //增加事务回滚
        int rows = userDao.deleteByUserId(userId);
        //成功，保存注销记录 sys_logout
        if (rows > 0) {
            userLogout.setCreateTime(Date.valueOf(String.valueOf(LocalDateTime.now())));
            userLogoutDao.insert(userLogout);
        }
        return rows;
    }

    @Override
    public Integer save(UserVo userVo) {
        System.out.println("doSaveService");
        User userSave = new User();

        String userName =  userVo.getUserName();
        Integer groupId = userVo.getGroupId();
        Integer roleId = userVo.getRoleId();
        String email = userVo.getEmail();
        String phone = userVo.getPhone();
        Integer sex = userVo.getSex();

        java.util.Date date = new java.util.Date();
        Date createTime = new java.sql.Date(date.getTime());
        Date updateTime = new java.sql.Date(date.getTime());


        userSave.setUserName(userName);
        userSave.setGroupId(groupId);
        userSave.setRoleId(roleId);
        userSave.setEmail(email);
        userSave.setPhone(phone);
        userSave.setSex(sex);
        userSave.setCreateTime(createTime);
        userSave.setUpdateTime(updateTime);
        userSave.setDelFlag(0);

        Integer insert = userDao.insertUser(userSave);

        return insert;
    }



    /**
    * Description: 修改用户
    * @param
    */
    @Override
    public Integer updateUser(UserVo userVo) {

        User user = new User();
        user.setUserId(userVo.getUserId());
        //更新修改时间
        user.setUpdateTime(Date.valueOf(LocalDate.now()));
        //调用sql方法
        Integer rows = userDao.updateUser(user);
        return rows;
    }
}
