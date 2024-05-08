package tyut.selab.userservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName sys_user
 */
public class User implements Serializable {
    /**
     * 主键id
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户所属小组编号
     */
    private Integer groupId;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 角色id
     */
    private Integer roleId;

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

    /**
     * 删除标识( 0 为正常 1 为删除)
     */
    private Integer delFlag;

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 主键id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 用户所属小组编号
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 用户所属小组编号
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 角色id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 用户性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 用户性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 删除标识( 0 为正常 1 为删除)
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标识( 0 为正常 1 为删除)
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", groupId=").append(groupId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", roleId=").append(roleId);
        sb.append(", email=").append(email);
        sb.append(", remark=").append(remark);
        sb.append(", phone=").append(phone);
        sb.append(", sex=").append(sex);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}