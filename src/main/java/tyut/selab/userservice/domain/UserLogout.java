package tyut.selab.userservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName sys_logout
 */
public class UserLogout implements Serializable {
    /**
     * 唯一标识
     */

    private Integer logoutId;

    /**
     *
     */
    private Integer userId;



    /**
     *
     */
    private Date createTime;

    /**
     * 操作管理员用户id
     */
    private Integer adminId;



    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
    public Integer getLogoutId() {
        return logoutId;
    }

    /**
     * 唯一标识
     */
    public void setLogoutId(Integer logoutId) {
        this.logoutId = logoutId;
    }

    /**
     *
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    /**
     *
     */
    public java.sql.Date getCreateTime() {
        return (java.sql.Date) createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 操作管理员用户id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 操作管理员用户id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
        UserLogout other = (UserLogout) that;
        return (this.getLogoutId() == null ? other.getLogoutId() == null : this.getLogoutId().equals(other.getLogoutId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogoutId() == null) ? 0 : getLogoutId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logoutId=").append(logoutId);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
