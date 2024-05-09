package tyut.selab.registration.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class RegistrationDto {
    /**
     * 主键id
     */
    private Integer registrationId;
    /**
     *面试者Name
     */
    private String intervieweesName;
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
    /**
     *面试时间
     */
    private LocalDateTime interviewTime;

    @Override
    public String toString() {
        return "RegistrationDto{" +
                "registrationId=" + registrationId +
                ", intervieweesName='" + intervieweesName + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", intentDepartment='" + intentDepartment + '\'' +
                ", classroom='" + classroom + '\'' +
                ", introduce='" + introduce + '\'' +
                ", purpose='" + purpose + '\'' +
                ", remark='" + remark + '\'' +
                ", interviewTime=" + interviewTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationDto that)) return false;
        return Objects.equals(getRegistrationId(), that.getRegistrationId()) && Objects.equals(getIntervieweesName(), that.getIntervieweesName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getIntentDepartment(), that.getIntentDepartment()) && Objects.equals(getClassroom(), that.getClassroom()) && Objects.equals(getIntroduce(), that.getIntroduce()) && Objects.equals(getPurpose(), that.getPurpose()) && Objects.equals(getRemark(), that.getRemark()) && Objects.equals(getInterviewTime(), that.getInterviewTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegistrationId(), getIntervieweesName(), getEmail(), getPhone(), getIntentDepartment(), getClassroom(), getIntroduce(), getPurpose(), getRemark(), getInterviewTime());
    }

    public Integer getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Integer registrationId) {
        this.registrationId = registrationId;
    }

    public String getIntervieweesName() {
        return intervieweesName;
    }

    public void setIntervieweesName(String intervieweesName) {
        this.intervieweesName = intervieweesName;
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

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(LocalDateTime interviewTime) {
        this.interviewTime = interviewTime;
    }

    public RegistrationDto() {
    }

    public RegistrationDto(Integer registrationId, String intervieweesName, String email, Integer phone, String intentDepartment, String classroom, String introduce, String purpose, String remark, LocalDateTime interviewTime) {
        this.registrationId = registrationId;
        this.intervieweesName = intervieweesName;
        this.email = email;
        this.phone = phone;
        this.intentDepartment = intentDepartment;
        this.classroom = classroom;
        this.introduce = introduce;
        this.purpose = purpose;
        this.remark = remark;
        this.interviewTime = interviewTime;
    }
}
