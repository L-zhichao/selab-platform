package tyut.selab.loginservice.dto;

import java.io.Serializable;

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
    private String password;

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

    public UserRegisterDto() {
    }

    public UserRegisterDto(String userName, Integer groupId, String email, String phone, Integer sex) {
        this.userName = userName;
        this.groupId = groupId;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "UserRegisterDto{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", groupId=" + groupId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                '}';
    }
}
