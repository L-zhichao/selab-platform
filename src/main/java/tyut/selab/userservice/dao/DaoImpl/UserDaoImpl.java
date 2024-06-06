package tyut.selab.userservice.dao.DaoImpl;

import com.alibaba.fastjson.JSONObject;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    /**
     * 增加用户
     * @return
     */
    //完成
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
        String password = user.getPassword();

        try {
            conn = JDBCUtils.getConnection();
            String sql1 = "insert into sys_user (user_name,create_time,update_time,role_id,email,phone,sex,del_flag,password) "+
                    "values (?,?,?,?,?,?,?,?,?)";
            pstmtInsert = conn.prepareStatement(sql1);
            pstmtInsert .setString(1,userName);
            pstmtInsert .setDate(2,createTime);
            pstmtInsert .setDate(3,updateTime);
            pstmtInsert .setInt(4,roleId);
            pstmtInsert .setString(5,email);
            pstmtInsert .setString(6,phone);
            pstmtInsert .setInt(7,sex);
            pstmtInsert .setInt(8,delFlag);
            pstmtInsert.setString(9,password);
            pstmtInsert .execute();

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
            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmtInsert);
            try {
                if(pstmtSelect != null) {
                    pstmtSelect.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }





    /**
     通过grouoId查询小组的名字
     */
    @Override
    public String getGroupName(Integer groupId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String groupName = null;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select group_name as groupName from sys_group where group_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,groupId);
            ResultSet resultSet =  pstmt.executeQuery();
            while (resultSet.next()){
                groupName = resultSet.getString("groupName");
            }

            return groupName;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }

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
        int rows;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE sys_user SET user_name=?,email=?,phone=?,sex=?,update_time=? where user_id=?";
            ps = conn.prepareStatement(sql);
            //是用if判断吗？？
            if (user.getUserName() != null) {
                ps.setString(1, user.getUserName());
            }
            if (user.getEmail() != null) {
                ps.setString(2, user.getEmail());
            }
            if (user.getPhone() != null) {
                ps.setString(3, user.getPhone());
            }
            if (user.getSex() != null) {
                ps.setInt(4, user.getSex());
            }
            if (user.getUpdateTime() != null) {
                ps.setTimestamp(5, new Timestamp(user.getUpdateTime().getTime()));
            }
            ps.setLong(6, user.getUserId());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return rows;
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
        int rows;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE sys_user SET role_id=? ,update_time = ? where user_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,user.getRoleId());
            ps.setTimestamp(2,new Timestamp(user.getUpdateTime().getTime()));
            ps.setLong(3,user.getUserId());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
        return rows;
    }

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */


    //基本完善
    @Override
    public User selectByUserIdUser(Long userId) {
        System.out.println("doSelectByUserIdUserDao");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user = new User();

        try {
            conn = JDBCUtils.getConnection();
            String sql1 = "select del_flag as delFlag , user_id as  userId , user_name as userName,role_id as roleId,email as email ,"+
                    "phone as phone,sex as sex ,create_time as createTime , update_time as updateTime from sys_user where user_id = ?";
            pstmt = conn.prepareStatement(sql1);
            pstmt.setLong(1,userId);
            resultSet = pstmt.executeQuery();
            //仅接收user表内容
            while (resultSet.next()){
                int del_flag = resultSet.getInt("delFlag");

                if (del_flag == 0) {
                    Long userid = resultSet.getLong("userId");
                    String username = resultSet.getString("userName");
                    Integer roleld = resultSet.getInt("roleId");
                    //rolename在业务层
                    //组名在业务层加
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    Integer sex = resultSet.getInt("sex");
                    Date createTime = resultSet.getDate("createTime");
                    Date updateTime = resultSet.getDate("updateTime");


                    user.setUserId(userid);
                    user.setUserName(username);
                    user.setRoleId(roleld);
                    user.setCreateTime(createTime);
                    user.setUpdateTime(updateTime);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setSex(sex);
                }
            }
            resultSet.close();
            //接收groupId
            String sql2 = "select group_id as groupId from user_group where user_id = ?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setLong(1,userId);
            ResultSet Group = pstmt.executeQuery();
            while (Group.next()){
                Integer groupId = Group.getInt("groupId");
                user.setGroupId(groupId);
            }
            Group.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }
        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> selectAll(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long userId = null;
        ArrayList<User> userArray = new ArrayList<>();


        try {
            conn = JDBCUtils.getConnection();
            String sql = "select del_flag as delFlag , user_id as  userId , user_name as userName,role_id as roleId,email as email ," +
                    "phone as phone,sex as sex ,create_time as createTime , update_time as updateTime from sys_user ";
            pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                int del_flag = resultSet.getInt("delFlag");

                if (del_flag == 0) {
                    userId = resultSet.getLong("userId");
                    String username = resultSet.getString("userName");
                    Integer roleld = resultSet.getInt("roleId");
                    //rolename在业务层
                    //组名在业务层加
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    Integer sex = resultSet.getInt("sex");
                    Date createTime = resultSet.getDate("createTime");
                    Date updateTime = resultSet.getDate("updateTime");


                    user.setUserId(userId);
                    user.setUserName(username);
                    user.setRoleId(roleld);
                    user.setCreateTime(createTime);
                    user.setUpdateTime(updateTime);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setSex(sex);
                }

                //接收groupId
                String sql2 = "select group_id as groupId from user_group where user_id = ?";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setLong(1, userId);
                ResultSet Group = pstmt.executeQuery();
                while (Group.next()) {
                    Integer groupId = Group.getInt("groupId");
                    user.setGroupId(groupId);
                }
                Group.close();
                userArray.add(user);
            }
            resultSet.close();
            return userArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);

        }

    }


        /**
     * 通过groupId查询用户信息
     * @param groupId
     * @return
     */
    @Override
    public List<User> selectByGroupIdUsers(Integer groupId) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Long> users = new ArrayList<>();
        ArrayList<User> userArray = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            String sql2 = "select user_id as userId from user_group where group_id = ?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1,groupId);
            ResultSet userIds = pstmt.executeQuery();
            while (userIds.next()){
                Long user = userIds.getLong("userId");
                users.add(user);
            }
            //根据users获取的userid获取对象
            for (Long userId: users){
                User user = selectByUserIdUser(userId);
                userArray.add(user);
            }
            return userArray;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }

    }
    /**
     *  通过用户名称查询用户信息
     * @param userName
     * @return
     */
    @Override
    public ArrayList<User> selectByUserName(String userName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long userId = null;
        ArrayList<User> userArray = new ArrayList<>();

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select del_flag as delFlag , user_id as  userId , user_name as userName,role_id as roleId,email as email ," +
                    "phone as phone,sex as sex ,create_time as createTime , update_time as updateTime from sys_user where user_name like ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"%"+userName+"%");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                User user = new User();
                int del_flag = resultSet.getInt("delFlag");

                if (del_flag == 0) {
                    userId = resultSet.getLong("userId");
                    String username = resultSet.getString("userName");
                    Integer roleld = resultSet.getInt("roleId");
                    //rolename在业务层
                    //组名在业务层加
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    Integer sex = resultSet.getInt("sex");
                    Date createTime = resultSet.getDate("createTime");
                    Date updateTime = resultSet.getDate("updateTime");


                    user.setUserId(userId);
                    user.setUserName(username);
                    user.setRoleId(roleld);
                    user.setCreateTime(createTime);
                    user.setUpdateTime(updateTime);
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setSex(sex);
                }
                resultSet.close();
                //接收groupId
                String sql2 = "select group_id as groupId from user_group where user_id = ?";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setLong(1,userId);
                ResultSet Group = pstmt.executeQuery();
                while (Group.next()){
                    Integer groupId = Group.getInt("groupId");
                    user.setGroupId(groupId);
                }
                Group.close();

                userArray.add(user);

            }
            return userArray;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }
    }

    /**
    * Description: 注销用户
    * @param userId
    * @return Integer
    */
    @Override
    public Integer deleteByUserId(Integer userId) {
        int rows;
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        try {
            conn = JDBCUtils.getConnection();
            //修改唯一标识，1为删除
            String sql1 = "UPDATE sys_user SET del_flag = 1 where user_id = ?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setLong(1,userId);
            ps1.executeUpdate();
            //删除用户
            String sql2 = "DELETE FROM sys_user WHERE user_id= ? ";
            ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, userId);
            rows = ps2.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, ps1);
            JDBCUtils.closeResource(conn, ps2);
        }
        return rows;
    }

    @Override
    public Integer updateGroup(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "UPDATE user_group SET group_id = ? where user_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,user.getGroupId());
            ps.setLong(2,user.getUserId());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }
        return rows;
    }
}
