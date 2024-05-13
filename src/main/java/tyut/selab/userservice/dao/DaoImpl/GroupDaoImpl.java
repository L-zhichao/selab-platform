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
    public Integer insert(Group group) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Integer groupId = group.getGroupId();
        String groupName = group.getGroupName();
        Integer parentId = group.getParentIdId();
        Date createTime = group.getCreateTime();
        Date updateTime = group.getUpdateTime();
        Integer updateUser = group.getUpdateUser();
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO sys_group (group_id,parent_id,group_name, create_time, update_time, update_user, del_flag )\n" +
                    "VALUES\n" +
                    "\t( ?,?,?,?,?,?,0 )";
            preparedStatement.setInt(1,groupId);
            preparedStatement.setInt(2,parentId);
            preparedStatement.setString(3,groupName);
            preparedStatement.setDate(4, (java.sql.Date) createTime);
            preparedStatement.setDate(5, (java.sql.Date) updateTime);
            preparedStatement.setInt(6,updateUser);
            preparedStatement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,preparedStatement);
        }
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
