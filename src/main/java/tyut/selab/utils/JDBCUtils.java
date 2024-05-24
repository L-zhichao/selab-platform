package tyut.selab.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**封装数据库连接和关闭**/
public class JDBCUtils {
    public static Connection getConnection() throws Exception{
        //读取配置文件基本信息
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.yml");
        Properties pros = new Properties();
        pros.load(is);

        String username = pros.getProperty("username");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClassName = pros.getProperty("driver-class-name");
        //加载驱动
        Class.forName(driverClassName);
        //获取连接
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }
    public static void closeResource(Connection conn, Statement ps){
        try {
            if(ps != null)
                ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(rs!=null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

