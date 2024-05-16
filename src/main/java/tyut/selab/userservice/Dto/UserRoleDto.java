package tyut.selab.userservice.Dto;

import java.io.Serializable;

/**
 * @className: UserRoleDto
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/11 16:03
 * @version: 1.0
 */
public class UserRoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     *  用户id
     */
    private Integer userId;

    public UserRoleDto() {
    }

    public UserRoleDto(Integer roleId, Integer userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
