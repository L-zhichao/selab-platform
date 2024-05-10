package tyut.selab.userservice.dao;

import tyut.selab.userservice.domain.UserLogout;

/**
 * 用户注销dao层接口
 */
public interface UserLogoutDao {

    /**
     * 增加注销记录
     * @param userLogout
     */
    public Integer insert(UserLogout userLogout);

}
