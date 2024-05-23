package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private UserLogoutDao userLogoutDao;

    /**
    * Description: 修改用户角色
    * @param userVo
    * @return Integer
    */
    @Override
    public Integer updateUserRole(UserVo userVo) {
        //转换对象类型 ？？？
        User user = null;
        user.setUserId(userVo.getUserId());

        //更新修改时间
        userVo.setUpdateTime(Date.valueOf(LocalDate.now()));
        //调用sql方法
        return userDao.updateUserRole(user);

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
        //注销用户 sys_user
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
        return null;
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
