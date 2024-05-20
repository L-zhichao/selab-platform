package tyut.selab.userservice.dao.DaoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //JdbcTemplate连接数据、库释放资源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils02.getDataSource());

    /**
     * 增加用户
     * @return
     */
    @Override
    public Integer insertUser() {
        return null;
    }



    /**
     * Description: 修改用户信息
     * @param user
     * @return Integer
     */
    @Override
    public Integer updateUser(User user) {
        //动态sql or 数据回显 ？？？

        //判断roleId 管理员修改所用，用户仅自己
        if (user.getUserId().equals(1) || user.getUserId().equals(2)) {
            String sql = "UPDATE sys_user SET user_name=?," +
                    "group_id=?,group_name=?," +
                    "email=?,phone=?,sex=?,where user_id=?";
            //调用JdbcTemplate方法，执行sql
            int rows = template.update(sql, 1,1,1,1,1,1,8);
            return rows;
        }
        if (user.getUserId().equals(3)) {
            String sql = "UPDATE sys_user SET user_name=?," +
                    "group_id=?,group_name=?" +
                    "email=?,phone=?,sex=? where userid=?";
            int rows = template.update(sql, 1,1,1,1,1,1,1, user.getUserId());
            return rows;
        }
        return null;
    }
    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */

    /**
    * Description: 修改用户权限
    * @param user
    * @return Integer
    */

    @Override
    public Integer updateUserRole(User user) {
        String sql = "UPDATE sys_user SET role_id=? where user_id=?";
        int rows = template.update(sql,1,user.getUserId());
        return rows;
    }

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */

    @Override
    public User selectByUserIdUser(Integer userId) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        User user = new User();

        try {
            //conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where user_id = ?";
            pstmt.setInt(1,userId);
            resultSet = pstmt.executeQuery();
            //仅接收user表内容

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
            //JDBCUtils.closeResource(conn,pstmt);
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
            //conn = JDBCUtils.getConnection();
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
            //conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where user_name = ?";
            pstmt.setString(1,userName);
            int count = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //JDBCUtils.closeResource(conn,pstmt);
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
        String sql = "DELETE FROM sys_user WHERE user_id=?";
        int rows = template.update(sql,userId);
        return rows;
    }
}
