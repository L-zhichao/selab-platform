package tyut.selab.userservice.dao.DaoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.UserLogout;

public class UserLogoutDaoImpl implements UserLogoutDao {

    private JdbcTemplate  template = new JdbcTemplate(JDBCUtils02.getDataSource());
    @Override
    public Integer insert(UserLogout userLogout) {
        String sql = "INSERT INTO user_logout VALUES(?,?,?,?)";
        //如何传递amdin_id,从token获取？
        int rows = template.update(sql,null,userLogout.getUserId(),userLogout.getCreateTime(),1);
        return 0;
    }
}
