package tyut.selab.taskservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName task_report
 */
public class TaskReport implements Serializable {
    /**
     * 主键唯一索引
     */
    private Integer reportId;

    /**
     * 
     */
    private Integer taskId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 汇报状态(1 已完成 0已红温)
     */
    private Integer reportStatus;

    /**
     * 汇报信息
     */
    private String details;

    /**
     * 
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 主键唯一索引
     */
    public Integer getReportId() {
        return reportId;
    }

    /**
     * 主键唯一索引
     */
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    /**
     * 
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 汇报状态(1 已完成 0已红温)
     */
    public Integer getReportStatus() {
        return reportStatus;
    }

    /**
     * 汇报状态(1 已完成 0已红温)
     */
    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    /**
     * 汇报信息
     */
    public String getDetails() {
        return details;
    }

    /**
     * 汇报信息
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskReport other = (TaskReport) that;
        return (this.getReportId() == null ? other.getReportId() == null : this.getReportId().equals(other.getReportId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getReportStatus() == null ? other.getReportStatus() == null : this.getReportStatus().equals(other.getReportStatus()))
            && (this.getDetails() == null ? other.getDetails() == null : this.getDetails().equals(other.getDetails()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReportId() == null) ? 0 : getReportId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getReportStatus() == null) ? 0 : getReportStatus().hashCode());
        result = prime * result + ((getDetails() == null) ? 0 : getDetails().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reportId=").append(reportId);
        sb.append(", taskId=").append(taskId);
        sb.append(", userId=").append(userId);
        sb.append(", reportStatus=").append(reportStatus);
        sb.append(", details=").append(details);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}