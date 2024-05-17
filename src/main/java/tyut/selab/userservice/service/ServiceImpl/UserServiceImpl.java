package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

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
        return null;
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
        return null;
    }

    @Override
    public Integer save(UserVo userVo) {
        return null;
    }



    /**
    * Description: 修改用户权限
    * @param
    */
    @Override
    public Integer updateUser(UserVo userVo) {

        //转换对象类型 ？？？
        User user = null;
        user.setUserId(userVo.getUserId());

        //更新修改时间
        userVo.setUpdateTime(Date.valueOf(LocalDate.now()));
        //调用sql方法
        return userDao.updateUser(user);
    }
}
