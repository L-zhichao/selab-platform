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


}
