package tyut.selab.taskservice.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @className: TaskInfoForUser
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 11:39
 * @version: 1.0
 */
public class TaskInfoForUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 发布者username
     */
    private String publisherName;

    /**
     * 任务名
     */
    private String name;

    /**
     * 任务内容
     */
    private String content;

    /**
     *  任务发布范围小组名称
     */
    private List<String> groupNames;

    /**
     * 截止时间<精确到时分秒>
     */
    private Date dealTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     *  任务状态 [1为已结束 0为未结束]
     */
    private Integer status;
    /**
     *  是否汇报(1为汇报 0为未汇报)
     */
    private Integer judge;
    /**
     *  汇报信息
     */
    private TaskReportVo taskReportVo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
    }

    public TaskReportVo getTaskReportVo() {
        return taskReportVo;
    }

    public void setTaskReportVo(TaskReportVo taskReportVo) {
        this.taskReportVo = taskReportVo;
    }




}
