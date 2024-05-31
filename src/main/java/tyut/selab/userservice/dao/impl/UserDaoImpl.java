package tyut.selab.userservice.dao.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class UserDaoImpl implements UserDao {
    @Override
    public Integer insertUser(User user) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count;
        Properties prop = new Properties();
        String userName = user.getUserName();
        Integer groupId = user.getGroupId();
        Integer roleId = user.getRoleId();
        String email = user.getEmail();
        String phone = user.getPhone();
        Integer sex = user.getSex();
        Integer delFlag = user.getDelFlag();
        String password = user.getPassword();
        String remark = user.getRemark();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "insert into sys_user(user_name,sys_user.create_time,update_time,role_id,email,remark,phone,sex,del_flag) values(?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement .setString(1,userName);
            preparedStatement .setTimestamp(2,new Timestamp(user.getCreateTime().getTime()));
            preparedStatement .setTimestamp(3,new Timestamp(user.getCreateTime().getTime()));
            preparedStatement .setInt(4,roleId);
            preparedStatement .setString(5,email);
            preparedStatement.setString(6,remark);
            preparedStatement .setString(7,phone);
            preparedStatement .setInt(8,sex);
            preparedStatement .setInt(9,delFlag);
            String sql1="insert into user_group (user_id,group_id) values (?,?)";
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
    public Integer updateUser(User user) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count=0;
        Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
                DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
                connection = dataSource.getConnection();
                String sql = "UPDATE sys_user SET user_name=?,sys_user.create_time=?,sys_user.update_time=?,sys_user.role_id=?,email=?,sys_user.remark=?,phone=?,sex=? where user_id=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement .setTimestamp(2,new Timestamp(user.getCreateTime().getTime()));
                preparedStatement .setTimestamp(3,new Timestamp(user.getCreateTime().getTime()));
                preparedStatement.setLong(4,user.getRoleId());
                preparedStatement.setString(5,user.getEmail());
                preparedStatement.setString(6,user.getRemark());
                preparedStatement.setString(7,user.getPhone());
                preparedStatement.setInt(8,user.getSex());
                preparedStatement.setLong(9,user.getUserId());
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
    public User selectByUserIdUser(Long userId) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet =null;
        User user=new User();
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "select * from sys_user where sys_user.user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int delFlag = resultSet.getInt("del_flag");
                if (delFlag==0){
                    String userName = resultSet.getString("user_name");
                    //int groupId = resultSet.getInt("group_id");
                    Date createTime = resultSet.getDate("create_time");
                    Date updateTime = resultSet.getDate("update_time");
                    int roleId = resultSet.getInt("role_id");
                    String email = resultSet.getString("email");
                    String remark = resultSet.getString("remark");
                    String phone = resultSet.getString("phone");
                    int sex = resultSet.getInt("sex");
                    //user = new User(userName,groupId,createTime,updateTime,roleId,email,remark,phone,sex,delFlag);
                    user.setUserName(userName);
                    //user.setGroupId(groupId);
                    user.setCreateTime(createTime);
                    user.setUpdateTime(updateTime);
                    user.setRoleId(roleId);
                    user.setEmail(email);
                    user.setRemark(remark);
                    user.setPhone(phone);
                    user.setSex(sex);
                }else {
                    throw new RuntimeException("此用户已删除");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet!=null)
                    resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        ResultSet resultSet1=null;
        try {
            String sql1="select group_id from user_group where user_id=?";
            preparedStatement=connection.prepareStatement(sql1);
            preparedStatement.setLong(1,userId);
            resultSet1 = preparedStatement.executeQuery();
            while (resultSet1.next()){
                Integer groupId=resultSet1.getInt("group_id");
                user.setGroupId(groupId);
            }
        } catch (SQLException e) {
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
                if (resultSet1!=null)
                    resultSet1.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    @Override
    public List<User> selectByGroupIdUsers(Integer groupId) {

        return null;
    }

    @Override
    public User selectByUserName(String userName) {
        return null;
    }

    @Override
    public String groupName(Integer groupId) {

        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet =null;
        String groupName = null;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "select group_name from sys_group where group_id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,groupId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                groupName=resultSet.getString("group_name");
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
        return groupName;
    }

    @Override
    public Integer groupUpdate(User user) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count=0;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "UPDATE user_group SET group_id=? where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getGroupId());
            preparedStatement.setLong(2,user.getUserId());
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

    /**
     * Cannot invoke "java.lang.Integer.intValue()" because the return value of "tyut.selab.userservice.domain.User.getRoleId()" is null
     * @param user
     * @return
     */

    @Override
    public Integer updateUserRole(User user) {
        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count=0;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "UPDATE sys_user SET sys_user.create_time=?,sys_user.update_time=?,sys_user.role_id=? where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement .setTimestamp(1,new Timestamp(user.getCreateTime().getTime()));
            preparedStatement .setTimestamp(2,new Timestamp(user.getCreateTime().getTime()));
            preparedStatement.setInt(3,user.getRoleId());
            preparedStatement.setLong(4,user.getUserId());
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
    public Integer deleteByUserId(Integer userId) {
        PreparedStatement preparedStatement=null;
        Connection connection = null;
        Integer count;
        PreparedStatement preparedStatement1=null;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "UPDATE sys_user SET del_flag = 1 where user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            String sql1="DELETE FROM sys_user WHERE user_id= ? ";
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1,userId);
            count = preparedStatement.executeUpdate()+preparedStatement1.executeUpdate();//影响的行数
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
                if (preparedStatement1!=null)
                    preparedStatement1.close();
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
