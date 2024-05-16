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

    public UserDto() {
    }

    public UserDto(String userName, Integer groupId, Integer groupName, Integer roleId, String roleName, String email, String remark, String phone, Integer sex) {
        this.userName = userName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.roleId = roleId;
        this.roleName = roleName;
        this.email = email;
        this.remark = remark;
        this.phone = phone;
        this.sex = sex;
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

    public Integer getGroupName() {
        return groupName;
    }

    public void setGroupName(Integer groupName) {
        this.groupName = groupName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
