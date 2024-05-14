package tyut.selab.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: JdbcUtils
 * Package: tyut.selab.utils
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/12 20:12
 * @Version 1.0
 */
public class JdbcUtil {
    private static DataSource dataSource = null;

    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    static {
        Properties properties = new Properties();
        InputStream ips = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        //线程本地变量中是否存在
        Connection connection = tl.get();
        //第一次没有
        if (connection == null) {
            //线程本地变量没有，连接池获取
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove();  //清空线程本地变量
            connection.setAutoCommit(true);  //事务状态回归
            connection.close();
        }
    }
}
