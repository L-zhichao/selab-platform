package tyut.selab.loginservice.dao;

import tyut.selab.loginservice.dto.UserRegisterDto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface UserDao {
    /**
     * 返回0表示没找到，返回1表示找到了
     * @param username
     * @return
     */
    public Integer findByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public UserRegisterDto getUserByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public Integer insertUser(UserRegisterDto user) throws SQLException;
}
