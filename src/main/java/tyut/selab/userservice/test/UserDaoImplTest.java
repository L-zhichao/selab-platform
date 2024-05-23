package tyut.selab.userservice.test;

import org.junit.Test;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDaoImplTest {

    @Test
    public void updateUserRole() {
        User user = new User();
        Connection conn = null;
        PreparedStatement ps = null;
        user.setRoleId(2);
        user.setUserId(1L);
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update sys_user set role_id=2 where user_id=1";
            ps = conn.prepareStatement(sql);

            ps.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.closeResource(conn,ps);
        }

    }


}
