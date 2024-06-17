package tyut.selab.loginservice.utils;

import tyut.selab.loginservice.dto.UserLocal;

import java.util.concurrent.ThreadLocalRandom;


/**
 * @className: SecurityUtil
 * @author: lizhichao
 * @description: TODO   除注册、登录接口外的所有接口请求头头必须加上Authorization  任务： 写一个过滤器获取这个属性值，在过滤器中调用token解析的方法获取到用户信息并春初到TheadLocal下的USerLcoal类中
 * @date: 2024/5/8 9:06
 * @version: 1.0
 */
public class SecurityUtil {
    private static final ThreadLocal<UserLocal> tl = new ThreadLocal<>();
    /**
     * 在用户登录后，调用该方法返回一个UserLocal对象的信息并将该对象的Token与前端传来的Token进行对比判断是否过期等
     * 在我们调用该方法前一定要先设置线程本地变量的值
     * @return 如果返回null值则没有登录，我们就在过滤器中不让他访问
     */
    public static UserLocal getUser(){
        return tl.get();
    }

    /**
     * 将我们首次登录后生成存有Token的UserLocal信息存入到ThreadLocal中
     * @param userLocal
     */
    public static void setUser(UserLocal userLocal){
        removeUser();
        tl.set(userLocal);
    }
    public static void removeUser(){
        tl.remove();
    }
    /**
     * 并发安全的方式生成六位数验证码
     * @return 返回一个六位数的验证码
     */
    public static String getRandom() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }
}
