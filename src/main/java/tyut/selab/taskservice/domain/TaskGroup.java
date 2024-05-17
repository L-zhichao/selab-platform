package tyut.selab.taskservice.domain;

import java.io.Serializable;

/**
 * 
 * @TableName task_group
 */
public class TaskGroup implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 小组id
     */
    private Integer groupId;

    private static final long serialVersionUID = 1L;

    public TaskGroup() {
    }

    public TaskGroup(Integer id, Integer taskId, Integer groupId) {
        this.id = id;
        this.taskId = taskId;
        this.groupId = groupId;
    }

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 任务id
     */
    public Integer getTaskId() {
        return taskId;
    }

    /**
     * 任务id
     */
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 小组id
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 小组id
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TaskGroup other = (TaskGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskId=").append(taskId);
        sb.append(", groupId=").append(groupId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}