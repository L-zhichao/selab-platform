package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.LoginService;

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
     * @return 返回的是
     */
    @Override
    public String login(UserLoginReq userLoginReq) {

        return null;
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
