package tyut.selab.loginservice.service;

import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;

public interface LoginService {

    /**
     *  用户登录
     * @param req
     * @return 返回相关登录信息的token
     */
    public String login(UserLoginReq req);

    /**
     *  用户注册
     * @param userRegisterDto
     * @return
     */
    public Integer register(UserRegisterDto userRegisterDto);
}
