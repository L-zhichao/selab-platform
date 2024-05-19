package tyut.selab.loginservice.dto;

import java.io.Serializable;

/**
 * @className: UserLoginReq
 * @author: lizhichao
 * @description: TODO 用户登录返回实体
 * @date: 2024/5/8 9:15
 * @version: 1.0
 */
public class UserLoginReq implements Serializable {

    private static final long  serialVersionUID = 1L;
    /**
     * 登录账号(可以是用户名，也可以是邮箱)
     */
    private String username;
    private String password;
    public UserLoginReq() {
    }

    public UserLoginReq(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
