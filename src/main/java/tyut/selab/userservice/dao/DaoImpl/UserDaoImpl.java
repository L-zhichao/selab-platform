package tyut.selab.userservice.dao.DaoImpl;

import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Integer insertUser() {
        return null;
    }

    @Override
    public Integer updateUser(User user) {
        return null;
    }

    @Override
    public User selectByUserIdUser(Integer userId) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from sys_user where user_id = ?";
            pstmt.setInt(1,userId);
            int count = pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,pstmt);
        }


        return null;
    }

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

    @Override
    public User selectByUserName(String userName) {
        return null;
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return null;
    }
}
