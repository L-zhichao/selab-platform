package tyut.selab.userservice.dao;

import org.junit.Test;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.User;

import java.util.ArrayList;
import java.util.List;

public class TestUserDao {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void testQueryByRoleId(){
        /*ArrayList<User> users = userDao.selectByRoleIdUsers(1);
        System.out.println(users);*/
        List<User> users = userDao.selectAll();
        System.out.println(users);
    }
}
