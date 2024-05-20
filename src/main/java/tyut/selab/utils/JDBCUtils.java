package tyut.selab.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**封装数据库连接和关闭**/
public class JDBCUtils {
    /*public static Connection getConnection() throws Exception{
        //读取配置文件基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String username = pros.getProperty("user");
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
    }*/

    private static DataSource ds ;

    private static final Object DruidDataSourceFactory = null;

    static {

        try {
            //1.加载配置文件
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("Druid.properties");
            pro.load(is);

            //2.初始化连接池对象
            ds = com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }


    //获取连接Connection对象
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }
    



}

