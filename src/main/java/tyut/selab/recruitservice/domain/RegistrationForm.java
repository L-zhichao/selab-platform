package tyut.selab.recruitservice.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
/**
 * 招新报名管理表
 * @TableName registration_form
 */
public class RegistrationForm {
    /**
     * 主键id
     */
    private Integer id;
    /**
     *面试者id
     */
    private Integer IntervieweesId;
    /**
     *邮箱地址
     */
    private String email;
    /**
     *联系方式
     */
    private String phone;
    /**
     *意向部门  1开发 2网安 3人工智能 4虚拟现实
     */
    private Integer intentDepartment;
    /**
     *  所属年级 [1为大一 2为大二 ...]
     */
    private Integer grade;
    /**
     *所属班级（注：class为关键字）
     */
    private String classroom;
    /**
     *面试时间
     */
    private Date interviewTime;
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
     *创建时间
     */
    private LocalDateTime initTime;
    /**
     *更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }
    public RegistrationForm(){}

    public RegistrationForm(Integer id, Integer intervieweesId, String email, String phone, Integer intentDepartment, String classroom, Date interviewTime, String introduce, String purpose, String remark, LocalDateTime initTime, Date updateTime) {
        this.id = id;
        IntervieweesId = intervieweesId;
        this.email = email;
        this.phone = phone;
        this.intentDepartment = intentDepartment;
        this.classroom = classroom;
        this.interviewTime = interviewTime;
        this.introduce = introduce;
        this.purpose = purpose;
        this.remark = remark;
        this.initTime = initTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "RegistrationVo{" +
                "id=" + id +
                ", IntervieweesId=" + IntervieweesId +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", intentDepartment='" + intentDepartment + '\'' +
                ", classroom='" + classroom + '\'' +
                ", interviewTime=" + interviewTime +
                ", introduce='" + introduce + '\'' +
                ", purpose='" + purpose + '\'' +
                ", remark='" + remark + '\'' +
                ", initTime=" + initTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistrationForm that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getIntervieweesId(), that.getIntervieweesId()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPhone(), that.getPhone()) && Objects.equals(getIntentDepartment(), that.getIntentDepartment()) && Objects.equals(getClassroom(), that.getClassroom()) && Objects.equals(getInterviewTime(), that.getInterviewTime()) && Objects.equals(getIntroduce(), that.getIntroduce()) && Objects.equals(getPurpose(), that.getPurpose()) && Objects.equals(getRemark(), that.getRemark()) && Objects.equals(getInitTime(), that.getInitTime()) && Objects.equals(getUpdateTime(), that.getUpdateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIntervieweesId(), getEmail(), getPhone(), getIntentDepartment(), getClassroom(), getInterviewTime(), getIntroduce(), getPurpose(), getRemark(), getInitTime(), getUpdateTime());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIntervieweesId() {
        return IntervieweesId;
    }

    public void setIntervieweesId(Integer intervieweesId) {
        IntervieweesId = intervieweesId;
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



    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
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

    public LocalDateTime getInitTime() {
        return initTime;
    }

    public void setInitTime(LocalDateTime initTime) {
        this.initTime = initTime;
    }

    public Integer getIntentDepartment() {
        return intentDepartment;
    }

    public void setIntentDepartment(Integer intentDepartment) {
        this.intentDepartment = intentDepartment;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}
