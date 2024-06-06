package tyut.selab.userservice.dao.DaoImpl;


import tyut.selab.userservice.common.Constant;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class UserLogoutDaoImpl implements UserLogoutDao {


    @Override
    public Integer insert(UserLogout userLogout) {

        Connection conn = null;
        PreparedStatement ps = null;
        int rows;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO sys_logout (user_id, create_time, admin_id) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, userLogout.getUserId());
            ps.setTimestamp(2, new Timestamp(userLogout.getCreateTime().getTime()));
            ps.setInt(3, userLogout.getAdminId());
            rows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
        return rows;
    }
}
