package tyut.selab.loginservice.dto;

/**
 * @className: UserLogin
 * @author: lizhichao
 * @description: TODO 登录保存用户信息
 * @date: 2024/5/8 9:07
 * @version: 1.0
 */
public class UserLocal {

    private Integer userId;
    private String userName;
    private Integer groupId;
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
