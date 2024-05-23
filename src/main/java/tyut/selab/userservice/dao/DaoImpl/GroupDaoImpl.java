package tyut.selab.userservice.dao.DaoImpl;

import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

public class GroupDaoImpl implements GroupDao {



    @Override
    public Integer insert(Group group) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String groupName = group.getGroupName();
        Integer parentId = group.getParentIdId();
//        Date createTime = group.getCreateTime();
//        Date updateTime = group.getUpdateTime();
        java.util.Date date = new java.util.Date();
        Integer updateUser = group.getUpdateUser();

        String sql = "INSERT INTO sys_group (parent_id,group_name, create_time, update_time, update_user, del_flag ) VALUES ( 1,'fufu','2024-05-21 12:03:12','2024-05-21 12:03:12',2,0 )";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
//        preparedStatement.setInt(1, parentId);
//        preparedStatement.setString(2, groupName);
//        preparedStatement.setDate(3,  new java.sql.Date(date.getTime()));
//        preparedStatement.setDate(4,  new java.sql.Date(date.getTime()));
//        preparedStatement.setInt(5, updateUser);
        preparedStatement.execute(sql);
        JDBCUtils.closeResource(conn, preparedStatement);

        return null;
    }

    @Override
    public Integer delete(Integer groupId) {
        return null;
    }

    @Override
    public List<Group> selectAllGroup() {
        return null;
    }

    @Override
    public Integer update(Group group) {
        return null;
    }
}
