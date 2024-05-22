package tyut.selab.taskservice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: TaskReportDto
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 11:33
 * @version: 1.0
 */
public class TaskReportDto implements Serializable {


    /**
     *
     */
    private Integer taskId;



    /**
     * 汇报状态(1 已完成 0已红温)
     */
    private Integer reportStatus;

    /**
     * 汇报信息
     */
    private String details;



    private static final long serialVersionUID = 1L;

    public TaskReportDto() {
    }

    public TaskReportDto(Integer taskId, Integer reportStatus, String details) {
        this.taskId = taskId;
        this.reportStatus = reportStatus;
        this.details = details;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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
}
