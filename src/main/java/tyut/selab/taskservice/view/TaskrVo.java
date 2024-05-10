package tyut.selab.taskservice.view;

import tyut.selab.userservice.vo.UserVo;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 任务管理vo表(后端传给前端参数)
 * @TableName task_manager
 */
public class TaskrVo {
    /**
     *主键id
     */
    private Integer taskId;
    /**
     *发布者
     */
    private UserVo publisher;
    /**
     *更新者
     */
    private UserVo updater;
    /**
     *任务小组
     */
    private UserVo groupName;
    /**
     *任务名
     */
    private String taskName;
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
     * 启用状态:（ 1/启用、0/停止）
     */
    private Integer status;

    @Override
    public String toString() {
        return "TaskrVo{" +
                "taskId=" + taskId +
                ", publisher=" + publisher +
                ", updater=" + updater +
                ", groupName=" + groupName +
                ", taskName='" + taskName + '\'' +
                ", content='" + content + '\'' +
                ", downStatus=" + downStatus +
                ", downTime=" + downTime +
                ", reportStatus=" + reportStatus +
                ", reportRemark='" + reportRemark + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskrVo that)) return false;
        return Objects.equals(getTaskId(), that.getTaskId()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getUpdater(), that.getUpdater()) && Objects.equals(getGroupName(), that.getGroupName()) && Objects.equals(getTaskName(), that.getTaskName()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getDownStatus(), that.getDownStatus()) && Objects.equals(getDownTime(), that.getDownTime()) && Objects.equals(getReportStatus(), that.getReportStatus()) && Objects.equals(getReportRemark(), that.getReportRemark()) && Objects.equals(getStartTime(), that.getStartTime()) && Objects.equals(getEndTime(), that.getEndTime()) && Objects.equals(getPublishTime(), that.getPublishTime()) && Objects.equals(getUpdateTime(), that.getUpdateTime()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getPublisher(), getUpdater(), getGroupName(), getTaskName(), getContent(), getDownStatus(), getDownTime(), getReportStatus(), getReportRemark(), getStartTime(), getEndTime(), getPublishTime(), getUpdateTime(), getStatus());
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public UserVo getPublisher() {
        return publisher;
    }

    public void setPublisher(UserVo publisher) {
        this.publisher = publisher;
    }

    public UserVo getUpdater() {
        return updater;
    }

    public void setUpdater(UserVo updater) {
        this.updater = updater;
    }

    public UserVo getGroupName() {
        return groupName;
    }

    public void setGroupName(UserVo groupName) {
        this.groupName = groupName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TaskrVo() {
    }

    public TaskrVo(Integer taskId, UserVo publisher, UserVo updater, UserVo groupName, String taskName, String content, Integer downStatus, LocalDateTime downTime, Integer reportStatus, String reportRemark, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime publishTime, LocalDateTime updateTime, Integer status) {
        this.taskId = taskId;
        this.publisher = publisher;
        this.updater = updater;
        this.groupName = groupName;
        this.taskName = taskName;
        this.content = content;
        this.downStatus = downStatus;
        this.downTime = downTime;
        this.reportStatus = reportStatus;
        this.reportRemark = reportRemark;
        this.startTime = startTime;
        this.endTime = endTime;
        this.publishTime = publishTime;
        this.updateTime = updateTime;
        this.status = status;
    }
}
