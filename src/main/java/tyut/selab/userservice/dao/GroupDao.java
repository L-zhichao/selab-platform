package tyut.selab.userservice.dao;

import tyut.selab.userservice.domain.Group;

import java.util.List;

/**
* @author 26580
* @description 针对表【sys_group(小组信息表)】的数据库操作Mapper
* @createDate 2024-05-05 23:29:38
* @Entity tyut.selab.userservice.domain.Group
*/
public interface GroupDao {


    public Integer insert(Group group);

    public Integer delete(Integer groupId);

    /**
     *  查询所有小组信息
     * @return
     */
    public List<Group> selectAllGroup();

    /**
     *  修改小组信息
     * @param group
     * @return
     */
     public Integer update(Group group);

}




