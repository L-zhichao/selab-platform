package tyut.selab.userservice.dao;

import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.vo.UserVo;

import java.util.ArrayList;
import java.util.List;


/**
* @author 26580
* @description 针对表【sys_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-05-04 17:39:55
* @Entity tyut.selab.userservice.domain.User
*/
public interface UserDao {




    /**
     * 增加用户
     * @return
     */
    public Integer insertUser(User user);

    /**
     * 查询所有用户
     */
    public List<User> selectAll(Integer cur,Integer size);

    /**
     *通过groupId
     */
    public String getGroupName(Integer groupId);

    /**
     *  修改用户
     * @param user
     * @return
     */
    public Integer updateUser(User user);

    /**
    * Description: 修改用户权限
    * @return Integer
    */
    public Integer updateUserRole(User user);

    /**
     * 通过用户id查询用户信息
     * @param userId
     * @return
     */
    public User selectByUserIdUser(Long userId);

    /**
     * 通过groupId查询用户信息
     * @param groupId
     * @return
     */
    public List<User> selectByGroupIdUsers(Integer groupId,Integer cur,Integer size);

    /**
     *  通过用户名称查询用户信息
     * @param userName
     * @return
     */
    public ArrayList<User> selectByUserName(String userName);

    /**
     *  通过用户id删除用户
     * @param userId
     * @return
     */
    public Integer deleteByUserId(Integer userId);

    public Integer updateGroup(User user);
}




