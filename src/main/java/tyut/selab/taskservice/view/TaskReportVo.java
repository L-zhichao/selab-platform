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
}
