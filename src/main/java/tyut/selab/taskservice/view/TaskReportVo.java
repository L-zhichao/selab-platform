package tyut.selab.taskservice.view;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: TaskReportVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 11:33
 * @version: 1.0
 */
public class TaskReportVo implements Serializable {
    /**
     * 主键唯一索引
     */
    private Integer reportId;

    /**
     *
     */
    private Integer taskId;

    /**
     * 汇报人
     */
    private String userName;

    /**
     * 汇报状态(1 已完成 0已红温)
     */
    private Integer reportStatus;

    /**
     * 汇报信息
     */
    private String details;

    /**
     * 汇报时间
     */
    private Date reportTime;

    private static final long serialVersionUID = 1L;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Integer reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
}
