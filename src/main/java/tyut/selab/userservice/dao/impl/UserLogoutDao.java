package tyut.selab.userservice.dao.impl;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.UserLogout;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

public class UserLogoutDao implements tyut.selab.userservice.dao.UserLogoutDao {
    @Override
    public Integer insert(UserLogout userLogout) {

        PreparedStatement preparedStatement;
        preparedStatement = null;
        Connection connection = null;
        Integer count;
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
            connection = dataSource.getConnection();
            String sql = "insert into sys_logout(user_id, create_time, admin_id) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userLogout.getUserId());
            preparedStatement.setTimestamp(2, new Timestamp(userLogout.getCreateTime().getTime()));
            preparedStatement.setInt(3, userLogout.getAdminId());
            count = preparedStatement.executeUpdate();//影响的行数
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection!=null)
                    connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }
}
