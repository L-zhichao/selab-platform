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

    public UserLocal() {
    }

    public UserLocal(Integer userId, String userName, Integer groupId, Integer roleId) {
        this.userId = userId;
        this.userName = userName;
        this.groupId = groupId;
        this.roleId = roleId;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取
     * @return groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取
     * @return roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String toString() {
        return "UserLocal{userId = " + userId + ", userName = " + userName + ", groupId = " + groupId + ", roleId = " + roleId + "}";
    }
}
