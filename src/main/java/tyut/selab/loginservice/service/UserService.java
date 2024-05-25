package tyut.selab.loginservice.service;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.dto.UserRegisterDto;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface UserService {
    public Integer findByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public UserRegisterDto getUserByUsername(String username) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
    public UserLocal getUserLocal();
    public Integer insertUser(UserRegisterDto user) throws SQLException;
}
