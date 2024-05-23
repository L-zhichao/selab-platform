package tyut.selab.userservice.dao.DaoImpl;

import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.JDBCUtils;

import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao {


    /**
     * 增加用户
     * @return
     */

    @Override
    public Integer insertUser(User user) {
        System.out.println("doInsertUserDao");
        Connection conn = null;
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtSelect = null;



        String userName =  user.getUserName();
        Integer groupId = user.getGroupId();
        Integer roleId = user.getRoleId();
        String email = user.getEmail();
        String phone = user.getPhone();
        Integer sex = user.getSex();
        Date createTime = (Date) user.getCreateTime();
        Date updateTime = (Date) user.getUpdateTime();
        Integer delFlag = user.getDelFlag();

        try {
            conn = JDBCUtils.getConnection();
            String sql1 = "insert into sys_user (user_name,create_time,update_time,role_id,email,phone,sex,del_flag) "+
                    "values (?,?,?,?,?,?,?,?)";
            pstmtInsert = conn.prepareStatement(sql1);
            pstmtInsert .setString(1,userName);
            pstmtInsert .setDate(2,createTime);
            pstmtInsert .setDate(3,updateTime);
            pstmtInsert .setInt(4,roleId);
            pstmtInsert .setString(5,email);
            pstmtInsert .setString(6,phone);
            pstmtInsert .setInt(7,sex);
            pstmtInsert .setInt(8,delFlag);
            pstmtInsert .executeUpdate();

            String sql2 = "select LAST_INSERT_ID() as user_id from sys_user; ";
            pstmtSelect = conn.prepareStatement(sql2);
            ResultSet resultSet = pstmtSelect.executeQuery();
            System.out.println(resultSet);
            Long userId = null;
            while (resultSet.next()) {
                 userId = resultSet.getLong("user_id");
            }
            resultSet.close();



            String sql3 = "insert into user_group (user_id,group_id) values (?,?)";
            pstmtInsert = conn.prepareStatement(sql3);
            pstmtInsert.setLong(1,userId);
            pstmtInsert.setInt(2,groupId);
            pstmtInsert.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmtInsert);
            try {
                pstmtSelect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return 0;
    }

    /**
     * Description: 修改用户信息
     * @param user
     * @return Integer
     */
    @Override
    public Integer updateUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE sys_user SET user_name=?,role_id=?,email=?,phone=?,sex=?,update_time=? where user_id=?";
            ps = conn.prepareStatement(sql);
            if (user.getUserName() != null) {
                ps.setString(1, user.getUserName());
            }
            if (user.getGroupId() != null) {
                ps.setLong(2, user.getGroupId());
            }
            //updatetime
            //角色id
            if (user.getEmail() != null) {
                ps.setString(4, user.getEmail());
            }
            if (user.getPhone() != null) {
                ps.setString(5, user.getPhone());
            }
            if (user.getSex() != null) {
                ps.setInt(5, user.getSex());
            }
            ps.execute(sql);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }

        return null;
    }
    /**
    * Description: 修改用户权限
    * @param user
    * @return Integer
    */

    @Override
    public Integer updateUserRole(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE sys_user SET role_id=? where user_id=?";
            ps.setInt(1,user.getRoleId());
            ps.setLong(2,user.getUserId());
            ps.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }

        return null;
    }

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */

    @Override
    public User selectByUserIdUser(Long userId) {
        System.out.println("doSelectByUserIdUserDao");
        System.out.println(userId);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user = new User();

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where user_id = ?";
            pstmt.setLong(1,userId);
            resultSet = pstmt.executeQuery(sql);
            //仅接收user表内容
            System.out.println(resultSet);
            int del_flag = resultSet.getInt("del_flag");

            if (del_flag != 0) {
                Long userid = resultSet.getLong("user_id");
                String username = resultSet.getString("user_name");
                Integer roleld = resultSet.getInt("role_id");
                //rolename在业务层
                //组id在group表里，组名在业务层加
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Integer sex = resultSet.getInt("sex");
                Date createTime = resultSet.getDate("create_time");
                Date updateTime = resultSet.getDate("update_time");



                user.setUserId(userid);
                user.setUserName(username);
                user.setRoleId(roleld);
                user.setCreateTime(createTime);
                user.setUpdateTime(updateTime);
                user.setEmail(email);
                user.setPhone(phone);
                user.setSex(sex);


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }
        return user;
    }
    /**
     * 通过groupId查询用户信息
     * @param groupId
     * @return
     */
    @Override
    public List<User> selectByGroupIdUsers(Integer groupId) {



        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where id = userId";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    /**
     *  通过用户名称查询用户信息
     * @param userName
     * @return
     */
    @Override
    public User selectByUserName(String userName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where user_name = ?";
            pstmt.setString(1,userName);
            int count = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }





        return null;
    }

    /**
    * Description: 注销用户
    * @param userId
    * @return Integer
    */
    @Override
    public Integer deleteByUserId(Integer userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "DELETE FROM sys_user WHERE user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,ps);
        }

        return null;
    }
}
