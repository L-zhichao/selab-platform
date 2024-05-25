package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.dao.UserDao;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.utils.BaseDao;
import tyut.selab.loginservice.utils.MD5util;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param username
     * @return
     */
    @Override
    public Integer findByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "select user_name as username from sys_user where user_name = ?";
        UserLoginReq user = executeQueryOne(UserLoginReq.class, sql, username);
        if(null != user){
            return 1;
        }
        return 0;
    }
    @Override
    public UserRegisterDto getUserByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String sql = "select user_name userName,password,email,sex,phone from sys_user where user_name =?";
        UserRegisterDto userRegistDto = executeQueryOne(UserRegisterDto.class, sql, username);
        return userRegistDto;
    }

    /**
     * 插入用户注册信息
     * @param user
     * @return 插入失败返回0，插入成功返回1
     */
    @Override
    public Integer insertUser(UserRegisterDto user) throws SQLException {
        String sql = "insert into sys_user (user_id user_name,password,create_time,update_time,role_id,email,phone,sex) " + "values(1,?,?,now(),now(),3,?,?,?)";
        int result = 0;
        result = executeUpdate(sql,user.getUserName(), MD5util.encrypt((user.getPassword())),user.getEmail(),user.getPhone(),user.getSex());
        return result;
    }
}
