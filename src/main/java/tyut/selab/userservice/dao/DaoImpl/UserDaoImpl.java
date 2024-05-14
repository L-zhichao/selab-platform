package tyut.selab.userservice.dao.DaoImpl;

import tyut.selab.userservice.Dto.UserDto;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.JDBCUtils;
import tyut.selab.utils.Result;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 增加用户
     * @return
     */
    @Override
    public Integer insertUser() {
        return null;
    }
    /**
     *  修改用户
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        return null;
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
            conn = JDBCUtils.getConnection();
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
     *  通过用户id删除用户
     * @param userId
     * @return
     */
    @Override
    public Integer deleteByUserId(Integer userId) {
        return null;
    }
}
