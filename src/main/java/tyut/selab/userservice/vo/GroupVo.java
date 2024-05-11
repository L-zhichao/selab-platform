package tyut.selab.userservice.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @className: GroupVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:12
 * @version: 1.0
 */
public class GroupVo implements Serializable {
    /**
     * 唯一标识
     */
    private Integer groupId;

    /**
     * 小组名称
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  所在组的所有用户
     */
    private List<UserVo> userVos;


    private static final long serialVersionUID = 1L;
}
