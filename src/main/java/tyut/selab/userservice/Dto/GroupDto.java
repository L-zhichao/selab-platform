package tyut.selab.userservice.Dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @className: GroupDto
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 10:09
 * @version: 1.0
 */
public class GroupDto implements Serializable {
    /**
     * 小组名称
     */
    private String groupName;



    @Serial
    private static final long serialVersionUID = 1L;

    public GroupDto(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
