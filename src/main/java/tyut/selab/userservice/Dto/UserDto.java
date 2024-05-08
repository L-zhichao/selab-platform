package tyut.selab.userservice.Dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: UserDto
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:28
 * @version: 1.0
 */
public class UserDto implements Serializable {


    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户所属小组编号
     */
    private Integer groupId;
    /**
     * 用户所属小组名称
     */
    private Integer groupName;

    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 绝色名称
     */
    private String roleName;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

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
