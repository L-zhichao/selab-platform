package tyut.selab.userservice.dao.DaoImpl;


import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.UserLogout;

public class UserLogoutDaoImpl implements UserLogoutDao {


    @Override
    public Integer insert(UserLogout userLogout) {
        String sql = "INSERT INTO user_logout VALUES(?,?,?,?)";

        //如何传递amdin_id,从token获取？

        return 0;
    }
}
