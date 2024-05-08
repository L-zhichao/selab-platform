package tyut.selab.loginservice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: UserRegisterDto
 * @author: lizhichao
 * @description: TODO 用户注册req接收对象
 * @date: 2024/5/8 9:34
 * @version: 1.0
 */
public class UserRegisterDto implements Serializable {

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户所属小组编号
     */
    private Integer groupId;


    /**
     * 邮箱地址
     */
    private String email;


    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private Integer sex;


    private static final long serialVersionUID = 1L;
}
