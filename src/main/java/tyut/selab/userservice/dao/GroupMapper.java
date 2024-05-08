package tyut.selab.userservice.dao;

import tyut.selab.userservice.domain.Group;

/**
* @author 26580
* @description 针对表【sys_group(小组信息表)】的数据库操作Mapper
* @createDate 2024-05-05 23:29:38
* @Entity tyut.selab.userservice.domain.Group
*/
public interface GroupMapper {


    public Integer insert(Group group);
    public Integer update(Group group);

}




