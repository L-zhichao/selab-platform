package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.JudgeRoleId;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    private UserLogoutDao userLogoutDao = new UserLogoutDaoImpl();

    //private Integer judgeRoleId = JudgeRoleId.GetJudgeRoleId();



    /**
    * Description: 修改用户角色
    * @param userVo
    * @return Integer
    */
    @Override
    public Integer updateUserRole(UserVo userVo) {
       /* SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();*/
        User userLocal = new User();
        userLocal.setRoleId(2);
        //判断是否为超级管理员
        if (userLocal.getRoleId().equals(1)) {
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setRoleId(userVo.getRoleId());
            //修改时间
            user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            //调用sql方法
            int rows =  userDao.updateUserRole(user);
            return rows;
        } else {
            return 0;
        }

        //工具类判断roleId
       /* if (judgeRoleId.equals(1)){
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setRoleId(userVo.getRoleId());
            //修改时间
            user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            //调用sql方法
            return userDao.updateUserRole(user);
        } else {
            return 0;
        }*/
    }
    /**
     * 查询所有用户
     */

    @Override
    public List<UserVo> queryAll(Integer cur,Integer size){

        List<User> users = userDao.selectAll(cur,size);
        List<UserVo> resultList = new ArrayList<>();

        for(User user:users) {
            UserVo userVo = new UserVo();

            Long userid = user.getUserId();
            String username = user.getUserName();
            Integer roleld = user.getRoleId();
            Integer groupId = user.getGroupId();
            String groupName = userDao.getGroupName(groupId);
            String email = user.getEmail();
            String phone = user.getPhone();
            Integer sex = user.getSex();

            Date createTime = (Date) user.getCreateTime();
            Date updateTime = (Date) user.getUpdateTime();

            //对rolename的判断
            if (roleld == 1) {

                userVo.setRoleName("超级管理员");
            } else if (roleld == 2) {
                userVo.setRoleName("管理员");

            } else {
                userVo.setRoleName("用户");
            }
            userVo.setUserName(username);
            userVo.setGroupId(groupId);
            userVo.setGroupName(groupName);
            userVo.setRoleId(roleld);
            userVo.setEmail(email);
            userVo.setPhone(phone);
            userVo.setSex(sex);
            userVo.setUserId(userid);
            userVo.setCreateTime(createTime);
            userVo.setUpdateTime(updateTime);

            resultList.add(userVo);
        }



        return resultList;
    }




    /**
    * Description: 根据小组id查询用户
    * @param groupId
    * @return List<UserVo>
    */
    @Override
    public List<UserVo> selectByGroupId(Integer groupId,Integer cur,Integer size) {

        String groupName = userDao.getGroupName(groupId);
        List<User> userArrayList = new ArrayList<>();
        List<UserVo> userVoList = new ArrayList<>();

        userArrayList = userDao.selectByGroupIdUsers(groupId,cur,size);


        for (User user : userArrayList){
            UserVo userVo = new UserVo();
            Long userid = user.getUserId();
            String username = user.getUserName();
            Integer roleld = user.getRoleId();
            String email = user.getEmail();
            String phone = user.getPhone();
            Integer sex = user.getSex();

            Date createTime = (Date) user.getCreateTime();
            Date updateTime = (Date) user.getUpdateTime();

            //对rolename的判断
            if(roleld == 1){
                userVo.setRoleName("超级管理员");
            } else if (roleld == 2) {
                userVo.setRoleName("管理员");
            }else{
                userVo.setRoleName("用户");
            }

            userVo.setUserName(username);
            userVo.setGroupId(groupId);
            userVo.setGroupName(groupName);
            userVo.setRoleId(roleld);
            userVo.setEmail(email);
            userVo.setPhone(phone);
            userVo.setSex(sex);
            userVo.setUserId(userid);
            userVo.setCreateTime(createTime);
            userVo.setUpdateTime(updateTime);

            userVoList.add(userVo);

        }
        return userVoList;
    }






   /**
   * Description: 根据用户id查询用户
   * @param userId
   * @return UserVo
   */
    @Override
    public UserVo selectByUserId(Long userId) {

        User userSelectByUserId = userDao.selectByUserIdUser(userId);

        UserVo userVo = new UserVo();


        Long userid = userSelectByUserId.getUserId();
        String username = userSelectByUserId.getUserName();
        Integer roleld = userSelectByUserId.getRoleId();
        Integer groupId= userSelectByUserId.getGroupId();
        String groupName = userDao.getGroupName(groupId);
        //组名在业务层加
        String email = userSelectByUserId.getEmail();
        String phone = userSelectByUserId.getPhone();
        Integer sex = userSelectByUserId.getSex();

        java.util.Date date = new java.util.Date();
        Date createTime = (Date) userSelectByUserId.getCreateTime();
        Date updateTime = (Date) userSelectByUserId.getUpdateTime();

        //对rolename的判断
        if(roleld == 1){

            userVo.setRoleName("超级管理员");
        } else if (roleld == 2) {
            userVo.setRoleName("管理员");

        }else{
            userVo.setRoleName("用户");
        }


        userVo.setUserName(username);
        userVo.setGroupId(groupId);
        userVo.setGroupName(groupName);
        userVo.setRoleId(roleld);
        userVo.setEmail(email);
        userVo.setPhone(phone);
        userVo.setSex(sex);
        userVo.setUserId(userid);
        userVo.setCreateTime(createTime);
        userVo.setUpdateTime(updateTime);

        return userVo;
    }


    /**
    * Description: 注销用户
    * @param userId,adminId
    * @return Integer
    */
    @Override
    public Integer delete(Integer userId) {
        //封装Userlogout数据
        SecurityUtil securityUtil = new SecurityUtil();
        //UserLocal userLocal = securityUtil.getUser();
        //以下为测试
        User userLocal = new User();
        userLocal.setRoleId(2);
        if (userLocal.getRoleId().equals(2)) {
            UserLogout userLogout = new UserLogout();
            userLogout.setUserId(userId);
            userLogout.setAdminId(userLocal.getRoleId());
            //增加事务回滚
            int rows = userDao.deleteByUserId(userLogout.getUserId());
            //成功，保存注销记录 sys_logout
            if (rows >= 1) {
                userLogout.setCreateTime(new Date(System.currentTimeMillis()));
                userLogoutDao.insert(userLogout);
            }
            return rows;
        } else {
            return 0;
        }

        /*SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();
        if (judgeRoleId.equals(2)){
            UserLogout userLogout = new UserLogout();
            userLogout.setUserId(userId);
            userLogout.setAdminId(userLocal.getRoleId());
            //增加事务回滚
            int rows = userDao.deleteByUserId(userLogout.getUserId());
            //成功，保存注销记录 sys_logout
            if (rows >= 1) {
                userLogout.setCreateTime(new Date(System.currentTimeMillis()));
                userLogoutDao.insert(userLogout);
            }
            return rows;
        } else {
            return 0;
        }*/
    }


    /**
    * Description: 新增用户信息
    * @param userVo
    * @return Integer
    */
    @Override
    public Integer save(UserVo userVo) {

        User userSave = new User();

        String userName =  userVo.getUserName();
        Integer groupId = userVo.getGroupId();
        Integer roleId = userVo.getRoleId();
        String email = userVo.getEmail();
        String phone = userVo.getPhone();
        Integer sex = userVo.getSex();
        String password = userVo.getPassword();

        java.util.Date date = new java.util.Date();
        Date createTime = new java.sql.Date(date.getTime());
        Date updateTime = new java.sql.Date(date.getTime());


        userSave.setUserName(userName);
        userSave.setGroupId(groupId);
        userSave.setRoleId(roleId);
        userSave.setEmail(email);
        userSave.setPhone(phone);
        userSave.setSex(sex);
        userSave.setCreateTime(createTime);
        userSave.setUpdateTime(updateTime);
        userSave.setDelFlag(0);
        userSave.setPassword(password);

        Integer insert = userDao.insertUser(userSave);

        return insert;
    }



   /**
   * Description: 修改用户信息
   * @param userVo
   * @return Integer
   */
    @Override
    public Integer updateUser(UserVo userVo) {
        /*SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();*/
        User userLocal = new User();
        userLocal.setRoleId(2);

        if (userLocal.getRoleId().equals(2)) {
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setSex(userVo.getSex());
            user.setUserName(userVo.getUserName());
            user.setEmail(userVo.getEmail());
            user.setPhone(userVo.getPhone());
            user.setGroupId(userVo.getGroupId());
            user.setRoleId(userVo.getRoleId());
            user.setUpdateTime(userVo.getUpdateTime());
            //调用sql方法
            Integer rows = userDao.updateUser(user);
            return rows;
        } else {
            return 0;
        }

        /*if (judgeRoleId.equals(2)){
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setSex(userVo.getSex());
            user.setUserName(userVo.getUserName());
            user.setEmail(userVo.getEmail());
            user.setPhone(userVo.getPhone());
            user.setGroupId(userVo.getGroupId());
            user.setRoleId(userVo.getRoleId());
            user.setUpdateTime(userVo.getUpdateTime());
            //调用sql方法
            Integer rows = userDao.updateUser(user);
            return rows;
        } else {
            return 0;
        }*/

    }


    /**
    * Description: 修改用户所属小组
    * @param userVo
    * @return Integer
    */
    @Override
    public Integer updateGroup(UserVo userVo) {
        /*SecurityUtil securityUtil = new SecurityUtil();
        UserLocal userLocal = securityUtil.getUser();*/
        User userLocal = new User();
        userLocal.setRoleId(2);
        if (userLocal.getRoleId().equals(2)) {
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setGroupId(userVo.getGroupId());
            int rows = userDao.updateGroup(user);
            return rows;
        } else {
            return 0;
        }

        /*if (judgeRoleId.equals(2)){
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setGroupId(userVo.getGroupId());
            return userDao.updateGroup(user);
        } else {
            return 0;
        }*/
    }

}
