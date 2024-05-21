package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dao.impl.UserDaoImpl;
import tyut.selab.loginservice.service.UserService;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param username
     * @return
     */
    @Override
    public Integer findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    /**
     * 查找用户信息如果有返回1，没有则返回0
     * @param password
     * @return
     */
    @Override
    public Integer findByPassword(String password) {
        return userDao.findByPassword(password);
    }
}
