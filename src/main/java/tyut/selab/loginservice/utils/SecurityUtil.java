package tyut.selab.loginservice.utils;

import tyut.selab.loginservice.dto.UserLocal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @className: SecurityUtil
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:06
 * @version: 1.0
 */
public class SecurityUtil {

    ThreadLocal<UserLocal> userLocal = new ThreadLocal<>();

    public static UserLocal getUser() {
        return null;
    }

    /**
     * 并发安全的方式生成六位数验证码
     * @return
     */
    public static String getRandom() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
    }
}
