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

    public GroupVo() {
    }

    public GroupVo(Integer groupId, String groupName, Date createTime, List<UserVo> userVos, Integer total, long serialVersionUID) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createTime = createTime;
        this.userVos = userVos;
    }

    /**
     * 获取
     * @return groupId
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置
     * @param groupId
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return userVos
     */
    public List<UserVo> getUserVos() {
        return userVos;
    }

    /**
     * 设置
     * @param userVos
     */
    public void setUserVos(List<UserVo> userVos) {
        this.userVos = userVos;
    }

    public String toString() {
        return "GroupVo{groupId = " + groupId + ", groupName = " + groupName + ", createTime = " + createTime + ", userVos = " + userVos + ", serialVersionUID = " + serialVersionUID + "}";
    }


}
