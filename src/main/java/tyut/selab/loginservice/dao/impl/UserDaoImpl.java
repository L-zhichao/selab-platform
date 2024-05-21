package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.dao.UserDao;
import tyut.selab.loginservice.dto.UserDto;
import tyut.selab.loginservice.utils.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param username
     * @return
     */
    @Override
    public Integer findByUsername(String username) {
        String sql = "select * from sys_user where username = ?";
        UserDto user = executeQueryOne(UserDto.class, sql, username);
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
        String sql = "select * from sys_user where password = ?";
        UserDto user = executeQueryOne(UserDto.class, sql, password);
        if(null != user){
            return 1;
        }
        return 0;
    }
}
