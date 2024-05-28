package tyut.selab.taskservice.dto;

import java.io.Serializable;

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


}
