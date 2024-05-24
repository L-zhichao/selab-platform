package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.LoginService;

/**
 * Classname: LoginServiceImpl
 * Description:
 *
 * @Author tt
 * @Creat 2024/5/20 19:49
 * @Version 17
 */
public class LoginServiceImpl implements LoginService {
    EmailServiceImpl serviceImpl = new EmailServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String login(UserLoginReq req) {
     /*   //        // 接收用户请求参数
//        // 获取要登录的用户名密码
//        UserLoginReq inputUser = WebUtils.readJson(req, UserLoginReq.class);
//        //实例化UserService
//        UserServiceImp userService = new UserServiceImp();
//        // 调用服务层方法,根据用户名查询数据库中是否有一个用户
//        UserLoginReq loginUser =userService.findByUsername(inputUser.getUsername());
//
//        Result result = new Result(null,null);
//
//        if(null == loginUser){
//            // 没有根据用户名找到用户,说明用户名有误
//            result=result.error(502,"not found user by username");
//        }else if(! loginUser.getPassword().equals(MD5util.encrypt(inputUser.getPassword()))){
//            // 用户密码有误,
//            result=result.error(503,"password failed");
//        }else{
//            // 登录成功
//            result=result.success(null);
//        }
//        WebUtils.writeJson(resp,result);*/
        return null;
    }

    @Override
    public Integer register(UserRegisterDto userRegisterDto) {

        return null;

    }

}
