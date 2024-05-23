package tyut.selab.userservice.test;

import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;

public class JDBCUtilsTest {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}
