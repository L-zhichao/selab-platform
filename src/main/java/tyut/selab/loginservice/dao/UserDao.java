package tyut.selab.loginservice.dao;

import tyut.selab.loginservice.dto.UserRegisterDto;

public interface UserDao {
    /**
     * 返回0表示没找到，返回1表示找到了
     * @param username
     * @return
     */
    public Integer findByUsername(String username);
    public Integer findByPassword(String password);
    public UserRegisterDto getUserByUsername(String username);
    public Integer insertUser(UserRegisterDto user);
}
