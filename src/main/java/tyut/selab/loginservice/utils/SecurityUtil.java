package tyut.selab.loginservice.utils;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.service.impl.UserServiceImpl;

import java.util.concurrent.ThreadLocalRandom;


/**
 * @className: SecurityUtil
 * @author: lizhichao
 * @description: TODO   除注册、登录接口外的所有接口请求头头必须加上Authorization  任务： 写一个过滤器获取这个属性值，在过滤器中调用token解析的方法获取到用户信息并春初到TheadLocal下的USerLcoal类中
 * @date: 2024/5/8 9:06
 * @version: 1.0
 */
public class SecurityUtil {
    private static ThreadLocal<UserLocal> userLocal = new ThreadLocal<>();
    UserServiceImpl userService = new UserServiceImpl();
    /**
     * 在用户登录后，调用该方法返回一个UserLocal对象的信息并将该对象赋值给
     * @return
     */
    public static UserLocal getUser(){
//        userService.getUserLocal();
        return null;
    }


    /**
     * 并发安全的方式生成六位数验证码
     * @return 返回一个六位数的验证码
     */
    public static String getRandom() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }
}
