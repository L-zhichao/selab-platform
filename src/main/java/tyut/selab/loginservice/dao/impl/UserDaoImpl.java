package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.dao.UserDao;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.utils.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param username
     * @return
     */
    @Override
    public Integer findByUsername(String username) {
        String sql = "select user_name as username from sys_user where user_name = ?";
        UserLoginReq user = executeQueryOne(UserLoginReq.class, sql, username);
        if(null != user){
            return 1;
        }
        return 0;
    }
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param password
     * @return
     */
    @Override
    public Integer findByPassword(String password) {
        String sql = "select password from sys_user where password = ?";
        String word = executeQueryOne(String.class, sql, password);
        if(null != word){
            return 1;
        }
        return 0;
    }

    @Override
    public UserRegisterDto getUserByUsername(String username) {
        String sql = "select user_name userName,password,email,sex,phone from sys_user where user_name =?";
        UserRegisterDto userRegistDto = executeQueryOne(UserRegisterDto.class, sql, username);
        return userRegistDto;
    }
}
