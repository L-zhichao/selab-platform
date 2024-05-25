package tyut.selab.userservice.service;

import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.vo.UserVo;

import java.util.List;

public interface UserService {

    /**
     * 修改用户角色信息
     * @param userVo
     * @return
     */
    public Integer updateUserRole(UserVo userVo);

    /**
     *  查询指定小组全部用户
     * @param groupId
     * @return
     */
    public List<UserVo> selectByGroupId(Integer groupId);

    /**
     *  通过id查询用户信息
     * @param userId
     * @return
     */
    public UserVo selectByUserId(Long userId);




    /**
     *  通过id删除用户
     * @param userId,adminId
     * @return
     */
    Integer delete(Integer userId, Integer adminId);

    /**
     * 新增用户
     * @param userVo
     * @return
     */
    public Integer save(UserVo userVo);

    /**
    * Description: 修改用户信息
    * @param userVo
    * @return 返回修改条数Integer
    */
    public Integer updateUser(UserVo userVo);
}
