package tyut.selab.userservice.service;

import tyut.selab.userservice.domain.User;

public interface UserService {

    /**
     * 修改用户角色信息
     * @param user
     * @return
     */
    public Integer updateUserRole(User user);
}
