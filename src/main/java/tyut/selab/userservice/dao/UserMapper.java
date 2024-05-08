package tyut.selab.userservice.dao;

import tyut.selab.userservice.domain.User;

import java.util.List;

/**
* @author 26580
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-05-04 17:39:55
* @Entity tyut.selab.userservice.domain.User
*/
public interface UserMapper {

    /**
     * 增加用户
     * @return
     */
    public Integer insertUser();

    /**
     *  修改用户
     * @param user
     * @return
     */
    public Integer updateUser(User user);

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */
    public User selectByUserIdUser(Integer userId);

    /**
     * 通过groupId查询用户信息
     * @param groupId
     * @return
     */
    public List<User> selectByGroupIdUsers(Integer groupId);

    /**
     *  通过用户名称查询用户信息
     * @param userName
     * @return
     */
    public User selectBYUserName(String userName);

    /**
     *  通过用户id删除用户
     * @param userId
     * @return
     */
    public Integer deleteByUserId(Integer userId);
}




