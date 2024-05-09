package tyut.selab.registration.view;

import tyut.selab.userservice.vo.UserVo;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistrationVo {
    /**
     * 主键id
     */
    private Integer id;
    /**
     *面试者Name
     */
    private UserVo Interviewees;
    /**
     *邮箱地址
     */
    private String email;
    /**
     *联系方式
     */
    private Integer phone;
    /**
     *意向部门
     */
    private String intentDepartment;
    /**
     *所属班级（注：class为关键字）
     */
    private String classroom;
    /**
     *面试时间
     */
    private LocalDateTime interviewTime;
    /**
     *个人介绍
     */
    private String introduce;
    /**
     *加入目的
     */
    private String purpose;
    /**
     *备注
     */
    private String remark;

    @Override
    public String toString() {
        return "RegistrationVo{" +
                "id=" + id +
                ", Interviewees=" + Interviewees +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", intentDepartment='" + intentDepartment + '\'' +
                ", classroom='" + classroom + '\'' +
                ", interviewTime=" + interviewTime +
                ", introduce='" + introduce + '\'' +
                ", purpose='" + purpose + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationVo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getInterviewees(), that.getInterviewees()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getIntentDepartment(), that.getIntentDepartment()) && Objects.equals(getClassroom(), that.getClassroom()) && Objects.equals(getInterviewTime(), that.getInterviewTime()) && Objects.equals(getIntroduce(), that.getIntroduce()) && Objects.equals(getPurpose(), that.getPurpose()) && Objects.equals(getRemark(), that.getRemark());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getInterviewees(), getEmail(), getPhone(), getIntentDepartment(), getClassroom(), getInterviewTime(), getIntroduce(), getPurpose(), getRemark());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserVo getInterviewees() {
        return Interviewees;
    }

    public void setInterviewees(UserVo interviewees) {
        Interviewees = interviewees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getIntentDepartment() {
        return intentDepartment;
    }

    public void setIntentDepartment(String intentDepartment) {
        this.intentDepartment = intentDepartment;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public RegistrationVo() {
    }

    public RegistrationVo(Integer id, UserVo interviewees, String email, Integer phone, String intentDepartment, String classroom, LocalDateTime interviewTime, String introduce, String purpose, String remark) {
        this.id = id;
        Interviewees = interviewees;
        this.email = email;
        this.phone = phone;
        this.intentDepartment = intentDepartment;
        this.classroom = classroom;
        this.interviewTime = interviewTime;
        this.introduce = introduce;
        this.purpose = purpose;
        this.remark = remark;
    }
}
