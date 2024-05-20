package tyut.selab.utils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils02 {
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
