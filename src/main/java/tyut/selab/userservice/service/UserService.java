package tyut.selab.userservice.service;

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
     * 查询指定小组全部用户
     *
     * @param groupId
     * @param cur
     * @param szie
     * @return
     */
    public List<UserVo> selectByGroupId(Integer groupId, Integer cur, Integer szie);
    public List<UserVo> selectGroupId(Integer groupId);

    /**
     *  通过id查询用户信息
     * @param userId
     * @return
     */
    public UserVo selectByUserId(Long userId);

    /**
     *  通过id删除用户
     * @param userId
     * @return
     */
    public Integer delete(Integer userId);

    /**
     * 新增用户
     * @param userVo
     * @return
     */
    public Integer save(UserVo userVo);
    public Integer update(UserVo userVo);

    Integer groupUpdate(UserVo userVo);

    List<UserVo> selectByRoleId(Integer roleId, Integer cur, Integer szie);

    List<UserVo> selectAll(Integer cur, Integer szie);

}
