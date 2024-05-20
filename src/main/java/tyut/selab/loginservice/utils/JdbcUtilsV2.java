package tyut.selab.loginservice.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 封装该类的目的是为了使注册驱动，获取连接，释放资源变得更容易，减少重复书写相同代码的次数，降低代码的耦合性
 */
public class JdbcUtilsV2 {
    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    static{
        Properties properties = new Properties();
        InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if(null == connection){
            tl.set(dataSource.getConnection());
            connection = tl.get();
        }
        return connection;
    }
    public static void freeConnection() throws SQLException {
        if(null != tl.get()) {
            tl.get().setAutoCommit(true);
            tl.get().close();
            tl.remove();
        }
    }

}
