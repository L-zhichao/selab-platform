package tyut.selab.userservice.service.ServiceImpl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.dao.DaoImpl.UserDaoImpl;
import tyut.selab.userservice.dao.DaoImpl.UserLogoutDaoImpl;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.UserLogoutDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.GroupService;
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
        //测试，判断roleId
        Integer loginRoleId = 1;
        if (loginRoleId.equals(1)) {
            if (userVo.getRoleId().equals(1)||userVo.getRoleId().equals(2)||userVo.getRoleId().equals(3)) {
                User user = new User();
                user.setUserId(userVo.getUserId());
                user.setRoleId(userVo.getRoleId());
                //修改时间
                user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                //调用sql方法
                int rows = userDao.updateUserRole(user);
                return rows;
            } else {
                //修改的roleId不存在
                return -1;
            }
        }
        //无管理员权限
        return 0;

       /* //工具类判断roleI
        if (judgeRoleId.equals(1)) {
            if (userVo.getRoleId() == 1 || userVo.getRoleId() == 2 || userVo.getRoleId() == 3) {
                User user = new User();
                user.setUserId(userVo.getUserId());
                user.setRoleId(userVo.getRoleId());
                //修改时间
                user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                //调用sql方法
                int rows = userDao.updateUserRole(user);
                return rows;
            } else {
                return -1;
            }
        }
        return 0;*/
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
        //测试,判断roleId
        Integer loginRoleId = 2;
        if (loginRoleId.equals(2)) {
            UserLogout userLogout = new UserLogout();
            userLogout.setUserId(userId);
            //userLogout.setAdminId(userLocal.getRoleId());
            userLogout.setAdminId(loginRoleId);
            int rows = userDao.deleteByUserId(userLogout.getUserId());
            //成功，保存注销记录 sys_logout
            if (rows >= 1) {
                userLogout.setCreateTime(new Date(System.currentTimeMillis()));
                userLogoutDao.insert(userLogout);
            }
            return rows;

        }
        //无管理员权限
        return 0;

        /*
        if (judgeRoleId.equals(2)) {
            UserLogout userLogout = new UserLogout();
            userLogout.setUserId(userId);
            userLogout.setAdminId(userLocal.getRoleId());
            userLogout.setAdminId(roleId);
            int rows = userDao.deleteByUserId(userLogout.getUserId());
            //成功，保存注销记录 sys_logout
            if (rows >= 1) {
                userLogout.setCreateTime(new Date(System.currentTimeMillis()));
                userLogoutDao.insert(userLogout);
            }
            return rows;
        }
        return 0;*/


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
        //测试，判断roleId
        Integer loginRoleId = 2;
        //正则判断
        Boolean falg1 = false;
        Boolean falg2 = false;
        String userNamePatten = "^[\\\\u4e00-\\\\u9fa5a-zA-Z0-9]*$" ;
        String emailPatten = "^[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+$";
        String phonePatten = "^[0-9]{11}$";
        if (userVo.getSex().equals(0)||userVo.getSex().equals(1)){
            falg1 = true;
        }
        if (userVo.getUserName().matches(userNamePatten) && userVo.getEmail().matches(emailPatten) && userVo.getPhone().matches(phonePatten)){
            falg2 = true;
        }
        //两个格式都正确才执行
        if (falg1 && falg2) {
            if (loginRoleId.equals(2)) {
                User user = new User();
                user.setUserId(userVo.getUserId());
                user.setSex(userVo.getSex());
                user.setUserName(userVo.getUserName());
                user.setEmail(userVo.getEmail());
                user.setPhone(userVo.getPhone());
                user.setUpdateTime(new Date(System.currentTimeMillis()));
                //调用sql方法
                Integer rows = userDao.updateUser(user);
                return rows;
            }
            //无管理员权限
            return 0;
        } else {
            //修改信息的格式不正确
            return -1;
        }

        /*if (judgeRoleId.equals(2)){
            User user = new User();
            user.setUserId(userVo.getUserId());
            user.setSex(userVo.getSex());
            user.setUserName(userVo.getUserName());
            user.setEmail(userVo.getEmail());
            user.setPhone(userVo.getPhone());
            user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
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
        //测试，判断roleId
        Integer loginRoleId = 2;
        User user = new User();
        GroupService groupService = new GroupServiceImpl();
        if (loginRoleId.equals(2)) {
            User selectUser = userDao.selectByUserIdUser(userVo.getUserId());
            if (selectUser.getDelFlag().equals(0)) {
                //判断修改后的groupId是否存在
                List<Integer> allGroupIds = groupService.selectAllGroupId();
                if (allGroupIds.contains(userVo.getGroupId())) {
                    //groupId存在，调用Dao方法
                    user.setUserId(userVo.getUserId());
                    user.setGroupId(userVo.getGroupId());
                    int rows = userDao.updateGroup(user);
                    return rows;
                } else {
                    //groupId不存在
                    return -1;
                }
            } else {
                //该用户不存在
                return -2;
            }
        }
        //无管理员权限
        return 0;
    }

    /*
    User user = new User();
    GroupService groupService = new GroupServiceImpl();
        if (judgeRoleId.equals(2)) {
        //调用查询当前groupId,判断修改后的groupId是否存在
        List<Integer> allGroupIds = groupService.selectAllGroupId();
        if (allGroupIds.contains(userVo.getGroupId())) {
            //groupId存在，调用Dao方法
            user.setUserId(userVo.getUserId());
            user.setGroupId(userVo.getGroupId());
            int rows = userDao.updateGroup(user);
            return rows;
        } else {
            //groupId不存在
            return -1;
        }
    }
    //无管理员权限
        return 0;
    }*/


}


