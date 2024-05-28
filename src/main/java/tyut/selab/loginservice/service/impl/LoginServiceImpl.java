package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.LoginService;
import tyut.selab.loginservice.utils.JwtHelperUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Classname: LoginServiceImpl
 * Description:
 *
 * @Author tt
 * @Creat 2024/5/20 19:49
 * @Version 17
 */
public class LoginServiceImpl implements LoginService {
    UserServiceImpl userService = new UserServiceImpl();

    /**
     * 该方法封装登录过程中需要对用户输入的账号密码进行判断的操作
     * @param userLoginReq 用户输入信息存储在该对象中
     * @return 返回的是根据用户相关信息生成的Token字符串
     */
    @Override
    public String login(UserLoginReq userLoginReq) throws SQLException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        UserLocal userLocal= userService.getUserLocal(userLoginReq.getUsername());
        String token = JwtHelperUtils.createToken(userLocal.getUserId(),userLocal.getUserName(),userLocal.getGroupId(),userLocal.getRoleId());
        return token;
    }

    /**
     * 将注册的用户信息存储到数据库中
     * @param userRegisterDto 用户填写的注册信息
     * @return 如果存储成功返回1，存储失败返回0
     */
    @Override
    public Integer register(UserRegisterDto userRegisterDto) throws SQLException {
        int rows = userService.insertUser(userRegisterDto);
        return rows;
    }

}
