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



}
