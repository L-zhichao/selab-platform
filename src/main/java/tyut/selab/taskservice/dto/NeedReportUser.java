package tyut.selab.taskservice.dto;

/**
 * @className: NeedReportUser
 * @author: lizhichao
 * @description: TODO  任务发布范围的所有用户
 * @date: 2024/5/10 16:06
 * @version: 1.0
 */
public class NeedReportUser {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsReport() {
        return isReport;
    }

    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
    }

    /**
     *  是否汇报
     */
    private Integer isReport;
}
