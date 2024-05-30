package tyut.selab.userservice.dao.DaoImpl;

import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupDaoImpl implements GroupDao {



    @Override
    public Integer insert(Group group) {

        try {
            //连接数据库
            Connection conn = JDBCUtils.getConnection();
            //获取对应数据
            String groupName = group.getGroupName();
            Integer parentId = group.getParentIdId();
//        Date createTime = group.getCreateTime();
//        Date updateTime = group.getUpdateTime();
            //获取当前时间方法用这个
            java.util.Date date = new java.util.Date();
            Integer updateUser = group.getUpdateUser();
            //查找是否有同名项，如果有，返回错误标记1
            String sql1 = "SELECT group_name FROM sys_group;";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                String groupName1 = resultSet.getString("group_name");
                if (groupName1.equals(groupName)) {
                    return 1;
                }
            }
            //sql语句，带占位符
            String sql = "INSERT INTO sys_group (parent_id,group_name,create_time,update_time,update_user) VALUES(?,?,?,?,?);";
            //预编译sql语句，注意括号里要写东西
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //set方法填充占位符的值
            preparedStatement.setInt(1, parentId);
            preparedStatement.setString(2, groupName);
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(5, updateUser);
            //执行修改过的sql语句，注意括号里别写东西
            preparedStatement.execute();
            //关闭数据库
            JDBCUtils.closeResource(conn, preparedStatement);
            //返回正确标记0
            return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer delete(Integer groupId) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "DELETE FROM sys_group WHERE group_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, groupId);
            int i = preparedStatement.executeUpdate();
            //判断是否执行
            JDBCUtils.closeResource(conn, preparedStatement);
            if (i == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> selectAllGroup(Integer cur, Integer szie) {
        List<Group> groups = new ArrayList<>();
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "SELECT * FROM sys_group LIMIT ?,?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, (cur - 1) * szie);
            preparedStatement.setInt(2, szie);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupId(resultSet.getInt("group_id"));
                group.setGroupName(resultSet.getString("group_name"));
                group.setCreateTime(resultSet.getTime("create_time"));
                groups.add(group);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public Integer update(Group group) {
        try {
            Connection conn = JDBCUtils.getConnection();
            String sql = "update sys_group set group_name = ?,create_time = ? ,update_time = ? where group_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,group.getGroupName());
            //时间类型转换
            java.sql.Date createTime = new java.sql.Date(group.getCreateTime().getTime());
            preparedStatement.setDate(2, createTime);
            java.util.Date date = new java.util.Date();
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(4,group.getGroupId());
            int i = preparedStatement.executeUpdate();
            JDBCUtils.closeResource(conn, preparedStatement);
            return i;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
