package tyut.selab.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTools {
    private static DataSource ds;
    static{
        try {
            Properties pro = new Properties();
            pro.load(new FileInputStream("C:\\Users\\lenovo\\IdeaProjects\\selab-platform\\src\\jdbc.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void free(Connection conn) throws SQLException {
        conn.setAutoCommit(true);
        conn.close();
    }
}