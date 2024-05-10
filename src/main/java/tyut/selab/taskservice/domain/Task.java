package tyut.selab.taskservice.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 任务管理表（与数据库字段一一对应）
 * @TableName task
 */
public class Task {
    /**
     *主键id
     */
    private Integer id;
    /**
     *发布者id
     */
    private Integer publisherId;
    /**
     *更新者id
     */
    private Integer updaterId;
    /**
     *任务小组id
     */
    private Integer groupId;
    /**
     *任务名
     */
    private String name;
    /**
     *任务内容
     */
    private String content;
    /**
     *完成状态（1/完成，2/已红温）
     */
    private Integer downStatus;
    /**
     *完成时间
     */
    private LocalDateTime downTime;
    /**
     *汇报状态（1/已完成,2/已红温）
     */
    private Integer reportStatus;
    /**
     *汇报备注信息
     */
    private String reportRemark;
    /**
     *开始时间
     */
    private LocalDateTime startTime;
    /**
     *结束时间
     */
    private LocalDateTime endTime;
    /**
     *发布时间
     */
    private LocalDateTime publishTime;
    /**
     *更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 删除状态:（ 1/删除、0/保存）
     */
    private Integer delFlag;
    /**
     * 启用状态:（ 1/启用、0/停止）
     */
    private Integer status;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", publisherId=" + publisherId +
                ", updaterId=" + updaterId +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", downStatus=" + downStatus +
                ", downTime=" + downTime +
                ", reportStatus=" + reportStatus +
                ", reportRemark='" + reportRemark + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getPublisherId(), that.getPublisherId()) && Objects.equals(getUpdaterId(), that.getUpdaterId()) && Objects.equals(getGroupId(), that.getGroupId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getDownStatus(), that.getDownStatus()) && Objects.equals(getDownTime(), that.getDownTime()) && Objects.equals(getReportStatus(), that.getReportStatus()) && Objects.equals(getReportRemark(), that.getReportRemark()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && Objects.equals(getPublishTime(), that.getPublishTime()) && Objects.equals(getUpdateTime(), that.getUpdateTime()) && Objects.equals(getDelFlag(), that.getDelFlag()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPublisherId(), getUpdaterId(), getGroupId(), getName(), getContent(), getDownStatus(), getDownTime(), getReportStatus(), getReportRemark(), getStartTime(), getEndTime(), getPublishTime(), getUpdateTime(), getDelFlag(), getStatus());
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDownStatus() {
        return downStatus;
    }

    public void setDownStatus(Integer downStatus) {
        this.downStatus = downStatus;
    }

    public LocalDateTime getDownTime() {
        return downTime;
    }

    public void setDownTime(LocalDateTime downTime) {
        this.downTime = downTime;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Task(Integer id, Integer publisherId, Integer updaterId, Integer groupId, String name, String content, Integer downStatus, LocalDateTime downTime, Integer reportStatus, String reportRemark, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime publishTime, LocalDateTime updateTime, Integer delFlag, Integer status) {
        this.id = id;
        this.publisherId = publisherId;
        this.updaterId = updaterId;
        this.groupId = groupId;
        this.name = name;
        this.content = content;
        this.downStatus = downStatus;
        this.downTime = downTime;
        this.reportStatus = reportStatus;
        this.reportRemark = reportRemark;
        this.startTime = startTime;
        this.endTime = endTime;
        this.publishTime = publishTime;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
        this.status = status;
    }
}
