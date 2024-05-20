package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dao.impl.UserLogoutDao;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.LoginService;
import tyut.selab.loginservice.utils.MD5util;

/**
 * Classname: LoginServiceImpl
 * Description:
 *
 * @Author tt
 * @Creat 2024/5/20 19:49
 * @Version 17
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public String login(UserLoginReq req) {
        return null;
    }

    @Override
    public Integer register(UserRegisterDto userRegisterDto) {
        userRegisterDto.setPassword(MD5util.encrypt(userRegisterDto.getPassword()));
        UserLogoutDao userLogoutDao = new UserLogoutDao();
        int rows = userLogoutDao.insert(userRegisterDto);
        return rows;
    }
    public UserLoginReq findByUsername(String name){
        return null;
    }
}
