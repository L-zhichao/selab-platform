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
    private Integer userId;
    private String email;
    private Date createTime;
    public Email() {
    }
    public Email(Integer userId, String email, Date createTime) {
        this.userId = userId;
        this.email = email;
        this.createTime = createTime;
    }

    public Email(String email) {
        this.email = email;
    }

    public Email(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
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
