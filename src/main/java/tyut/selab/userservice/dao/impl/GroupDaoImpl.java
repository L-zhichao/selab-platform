package tyut.selab.userservice.dao.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.sun.nio.file.ExtendedWatchEventModifier;
import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.utils.Result;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GroupDaoImpl implements GroupDao {
    @Override
    public Integer insert(Group group) {
        String groupName = group.getGroupName();
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        PreparedStatement preparedStatement1=null;
        ResultSet resultSet=null;
        Integer count;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            //加上s前端报错33-46
            String s="select sys_group.group_name from sys_group";
            preparedStatement1= connection.prepareStatement(s);
            resultSet=preparedStatement1.executeQuery();
            while (resultSet.next()){
                String groupName1 = resultSet.getString("group_name");

                if (groupName1.equals(groupName)){
                    return 0;
                }


            }
            String sql = "insert into sys_group(parent_id,group_name,sys_group.create_time,update_time,update_user) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, group.getParentId());
            preparedStatement.setString(2, group.getGroupName());
            preparedStatement.setTimestamp(3, new Timestamp(group.getCreateTime().getTime()));
            preparedStatement.setTimestamp(4, new Timestamp(group.getCreateTime().getTime()));
            preparedStatement.setInt(5, group.getUpdateUser());
            count = preparedStatement.executeUpdate();//影响的行数
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection!=null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement1!=null)
                    preparedStatement1.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;

    }

    @Override
    public Integer delete(Integer groupId) {
        PreparedStatement preparedStatement=null;
        Connection connection = null;
        Integer count;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "delete from sys_group where group_id =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, groupId);
            count = preparedStatement.executeUpdate();//影响的行数
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection!=null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }

    @Override
    public List<Group> selectAllGroup(Integer cur,Integer szie) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet =null;
        Group group;
        ArrayList<Group> list = new ArrayList<>();
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "select * from sys_group limit ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,(cur-1)*szie);
            preparedStatement.setInt(2,szie);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                    int delFlag = resultSet.getInt("del_flag");
                //if (delFlag==0){
                    String groupName = resultSet.getString("group_name");
                    int groupId = resultSet.getInt("group_id");
                    Date createTime = resultSet.getDate("create_time");
                    group = new Group();
                    group.setGroupId(groupId);
                    group.setGroupName(groupName);
                    group.setCreateTime(createTime);
                    list.add(group);
                //}else {
                //    throw new RuntimeException("此小组已删除");
                //}
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement!=null)
                     preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection!=null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public Integer update(Group group) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "update sys_group set group_name=?,sys_group.create_time=?,update_time=?,update_user=? where group_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, group.getGroupName());
            java.sql.Date createTime = new java.sql.Date(group.getCreateTime().getTime());
            preparedStatement.setTimestamp(2, new Timestamp(group.getCreateTime().getTime()));
            preparedStatement.setTimestamp(3, new Timestamp(group.getCreateTime().getTime()));
            preparedStatement.setInt(4, group.getUpdateUser());
            preparedStatement.setInt(5,group.getGroupId());
            count = preparedStatement.executeUpdate();//影响的行数
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection!=null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }


}
