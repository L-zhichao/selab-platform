package tyut.selab.userservice.dao.DaoImpl;


import tyut.selab.userservice.common.Constant;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserLogoutDaoImpl implements UserLogoutDao {


    @Override
    public Integer insert(UserLogout userLogout) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO sys_logout VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userLogout.getUserId());
            ps.setDate(2, new java.sql.Date(userLogout.getCreateTime().getTime()));
            ps.setInt(3,userLogout.getAdminId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        //如何传递amdin_id,从token获取？

        return 0;
    }
}
