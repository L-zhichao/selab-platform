package tyut.selab.loginservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Email
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:24
 * @version: 1.0
 */
public class Email implements Serializable {

    public static final Long serialVersionUID = 1L;
    private String email;
    private Integer userId;
    private Date createTime;
    public Email() {
    }

    public Email(String email, Integer userId, Date createTime) {
        this.email = email;
        this.userId = userId;
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Email{" +
                "email='" + email + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}
