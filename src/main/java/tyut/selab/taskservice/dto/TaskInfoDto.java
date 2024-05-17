package tyut.selab.taskservice.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @className: TaskInfoDto
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/10 11:19
 * @version: 1.0
 */
public class TaskInfoDto implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 发布者userId
     */
    private Integer publisherId;

    /**
     * 更新者id
     */
    private Integer updaterId;

    /**
     * 任务名
     */
    private String name;

    /**
     * 任务内容
     */
    private String content;

    /**
     *  发布任务范围小组
     */
    private List<Integer> groupIds;

    /**
     * 截止时间<精确到时分秒>
     */
    private Date dealTime;

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

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Integer> groupIds) {
        this.groupIds = groupIds;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }
}
