package tyut.selab.recruitservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class RegistrationDto {

    /**
     *邮箱地址
     */
    private String email;
    /**
     *联系方式
     */
    private Integer phone;
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
     *个人介绍
     */
    private String introduce;
    /**
     *加入目的
     */
    private String purpose;

    /**
     *面试时间
     */
    private Date interviewTime;

    @Override
    public String toString() {
        return "RegistrationDto{" +
                "email='" + email + '\'' +
                ", phone=" + phone +
                ", intentDepartment=" + intentDepartment +
                ", grade=" + grade +
                ", classroom='" + classroom + '\'' +
                ", introduce='" + introduce + '\'' +
                ", purpose='" + purpose + '\'' +
                ", interviewTime=" + interviewTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationDto that = (RegistrationDto) o;
        return Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(intentDepartment, that.intentDepartment) && Objects.equals(grade, that.grade) && Objects.equals(classroom, that.classroom) && Objects.equals(introduce, that.introduce) && Objects.equals(purpose, that.purpose) && Objects.equals(interviewTime, that.interviewTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone, intentDepartment, grade, classroom, introduce, purpose, interviewTime);
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


    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public RegistrationDto() {
    }

    public RegistrationDto(String email, Integer phone, Integer intentDepartment, Integer grade, String classroom, String introduce, String purpose, Date interviewTime) {
        this.email = email;
        this.phone = phone;
        this.intentDepartment = intentDepartment;
        this.grade = grade;
        this.classroom = classroom;
        this.introduce = introduce;
        this.purpose = purpose;
        this.interviewTime = interviewTime;
    }
}
