package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        //dto vo??

        User user = new User();
        user.setUserId(userVo.getUserId());
        //user为Date,sql为DateTime
        user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        //调用sql方法
        return userDao.updateUserRole(user);

    }

    @Override
    public List<UserVo> selectByGroupId(Integer groupId) {

        String groupName = userDao.getGroupName(groupId);
        List<User> userArrayList = new ArrayList<>();
        List<UserVo> userVoList = new ArrayList<>();
        UserVo userVo = new UserVo();
        userArrayList = userDao.selectByGroupIdUsers(groupId);


        for (User user : userArrayList){
            Long userid = user.getUserId();
            String username = user.getUserName();
            Integer roleld = user.getRoleId();
            String email = user.getEmail();
            String phone = user.getPhone();
            Integer sex = user.getSex();
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
            userVo.setGroupId(groupId);
            userVo.setGroupName(groupName);
            userVo.setRoleId(roleld);
            userVo.setEmail(email);
            userVo.setPhone(phone);
            userVo.setSex(sex);
            userVo.setUserId(userid);
            userVo.setCreateTime(createTime);
            userVo.setUpdateTime(updateTime);

            userVoList.add(userVo);

        }
        return userVoList;
    }

    @Override
    public UserVo selectByUserId(Long userId) {
        System.out.println("doSelectByUserIdService");
        User userSelectByUserId = userDao.selectByUserIdUser(userId);

        UserVo userVo = new UserVo();


        Long userid = userSelectByUserId.getUserId();
        String username = userSelectByUserId.getUserName();
        Integer roleld = userSelectByUserId.getRoleId();
        Integer groupId= userSelectByUserId.getGroupId();
        String groupName = userDao.getGroupName(groupId);
        //组名在业务层加
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
        userVo.setGroupId(groupId);
        userVo.setGroupName(groupName);
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
    * @param userId,adminId
    * @return Integer
    */
    @Override
    public Integer delete(Integer userId, Integer adminId) {
        //封装Userlogout数据
        UserLogout userLogout = new UserLogout();
        userLogout.setUserId(userId);
        userLogout.setAdminId(adminId);
        //增加事务回滚
        int rows = userDao.deleteByUserId(userLogout.getUserId());
        //成功，保存注销记录 sys_logout
        if (rows >= 1) {
            userLogout.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
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
        user.setSex(userVo.getSex());
        user.setUserName(userVo.getUserName());
        user.setEmail(userVo.getEmail());
        user.setPhone(userVo.getPhone());
        user.setGroupId(userVo.getGroupId());
        user.setRoleId(userVo.getRoleId());
        user.setUpdateTime(userVo.getUpdateTime());
        //调用sql方法
        Integer rows = userDao.updateUser(user);
        return rows;
    }
}
