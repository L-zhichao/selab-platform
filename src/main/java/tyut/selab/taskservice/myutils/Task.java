package tyut.selab.taskservice.myutils;

/**
 * ClassName: Task
 * Package: tyut.selab.taskservice.myutils
 * Description:
 *
 * @Author LLLLB
 * @Create 2024/5/29 22:50
 * @Version 1.0
 */
public class Task {
    private Integer taskId;
    private Integer userId;
    private Integer reportId;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
