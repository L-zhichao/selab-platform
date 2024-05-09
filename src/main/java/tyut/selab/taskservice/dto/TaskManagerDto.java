package tyut.selab.taskservice.dto;

import java.util.Objects;

/**
 * 任务管理param表（后端接受前端传来参数）
 * @TableName task_manager
 */
public class TaskManagerDto {
    /**
     *主键id
     */
    private Integer taskId;
    /**
     *发布者
     */
    private String publisher;
    /**
     *更新者
     */
    private String updater;
    /**
     *任务小组名
     */
    private String groupName;
    /**
     *任务名
     */
    private String taskName;
    /**
     *任务内容
     */
    private String content;
    /**
     *汇报备注信息
     */
    private String reportRemark;
    /**
     * 启用状态:（ 1/启用、0/停止）
     */
    private Integer status;

    @Override
    public String toString() {
        return "TaskManagerDto{" +
                "taskId=" + taskId +
                ", publisher='" + publisher + '\'' +
                ", updater='" + updater + '\'' +
                ", groupName='" + groupName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", content='" + content + '\'' +
                ", reportRemark='" + reportRemark + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskManagerDto that)) return false;
        return Objects.equals(getTaskId(), that.getTaskId()) && Objects.equals(getPublisher(), that.getPublisher()) && Objects.equals(getUpdater(), that.getUpdater()) && Objects.equals(getGroupName(), that.getGroupName()) && Objects.equals(getTaskName(), that.getTaskName()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getReportRemark(), that.getReportRemark()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getPublisher(), getUpdater(), getGroupName(), getTaskName(), getContent(), getReportRemark(), getStatus());
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
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

    public String getReportRemark() {
        return reportRemark;
    }

    public void setReportRemark(String reportRemark) {
        this.reportRemark = reportRemark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TaskManagerDto() {
    }

    public TaskManagerDto(Integer taskId, String publisher, String updater, String groupName, String taskName, String content, String reportRemark, Integer status) {
        this.taskId = taskId;
        this.publisher = publisher;
        this.updater = updater;
        this.groupName = groupName;
        this.taskName = taskName;
        this.content = content;
        this.reportRemark = reportRemark;
        this.status = status;
    }
}
