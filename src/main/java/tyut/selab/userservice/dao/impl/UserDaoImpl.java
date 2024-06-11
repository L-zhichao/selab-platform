package tyut.selab.userservice.dao.impl;

import tyut.selab.bookservice.dao.BaseDao;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;

import java.util.List;

/**
 * ClassName: UserDaoimpl
 * Package: tyut.selab.userservice.dao.impl
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/16 20:39
 * @Version 1.0
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public Integer insertUser() {
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        return null;
    }

    @Override
    public User selectByUserIdUser(Integer userId) {
        String sql = "select user_id userId,user_name userName,group_id groupId,create_time createTime,update_time updateTime,role_id roleId,email,remark,phone,sex,del_flag delFlag from sys_user where user_id = ";
        sql = sql + userId + ";";
        List<Object> objects = baseQuery(User.class, sql);
        return (User) objects.get(0);
    }

    @Override
    public List<User> selectByGroupIdUsers(Integer groupId) {
        return null;
    }

    @Override
    public User selectByUserName(String userName) {
        return null;
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return null;
    }
}
