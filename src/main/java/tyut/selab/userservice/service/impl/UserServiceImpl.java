package tyut.selab.userservice.service.impl;

import tyut.selab.userservice.dao.GroupDao;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.GroupDaoImpl;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.dao.impl.UserLogoutDao;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.domain.UserLogout;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.vo.UserVo;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();


    @Override
    public List<UserVo> selectGroupId(Integer groupId) {
        ArrayList<UserVo> userVos = new ArrayList<>();
        List<User> users = userDao.selectGroupIdUsers(groupId);
        //if (!users.isEmpty()){

            for (User user: users){
                UserVo userVo = new UserVo();
                Long userId = user.getUserId();
                String userName = user.getUserName();
                String groupName = userDao.getgroupName(groupId);
                Integer roleId = user.getRoleId();
                String email = user.getEmail();
                String phone = user.getPhone();
                Integer sex = user.getSex();
                userVo.setUserName(userName);
                userVo.setGroupId(groupId);
                userVo.setGroupName(groupName);
                userVo.setRoleId(roleId);
                if(roleId == 1){
                    userVo.setRoleName("超级管理员");
                } else if (roleId == 2) {
                    userVo.setRoleName("管理员");

                }else if(roleId==3){
                    userVo.setRoleName("用户");
                }/*else {
            throw new RuntimeException("找不到该用户类别");
            }*/
                userVo.setEmail(email);
                userVo.setPhone(phone);
                userVo.setSex(sex);
                userVo.setUserId(userId);
                userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                userVos.add(userVo);
            }

            return userVos;
        //}
        //return null;
    }

    @Override
    public Integer updateUserRole(UserVo userVo) {
        //Date date = new Date();
        User user = new User();

        Long userId = userVo.getUserId();
        Integer roleId = userVo.getRoleId();
        user.setUserId(userId);
        if (roleId != 1 && roleId != 2 && roleId != 3) {
            return 0;
        }
        user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setRoleId(roleId);
        user.setDelFlag(0);
        Integer updated = userDao.updateUserRole(user);
        return updated;
    }

    @Override
    public List<UserVo> selectByGroupId(Integer groupId, Integer cur, Integer szie) {
        ArrayList<UserVo> userVos = new ArrayList<>();
        List<User> users = userDao.selectByGroupIdUsers(groupId,cur,szie);
        //if(!users.isEmpty()){

            for (User user: users){
                UserVo userVo = new UserVo();
                Long userId = user.getUserId();
                String userName = user.getUserName();
                String groupName = userDao.getgroupName(groupId);
                Integer roleId = user.getRoleId();
                String email = user.getEmail();
                String phone = user.getPhone();
                Integer sex = user.getSex();
                userVo.setUserName(userName);
                userVo.setGroupId(groupId);
                userVo.setGroupName(groupName);
                userVo.setRoleId(roleId);
                if(roleId == 1){
                    userVo.setRoleName("超级管理员");
                } else if (roleId == 2) {
                    userVo.setRoleName("管理员");

                }else if(roleId==3){
                    userVo.setRoleName("用户");
                }/*else {
            throw new RuntimeException("找不到该用户类别");
            }*/
                userVo.setEmail(email);
                userVo.setPhone(phone);
                userVo.setSex(sex);
                userVo.setUserId(userId);
                userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                userVos.add(userVo);
            }
            return userVos;
        //}
        //return null;
    }

    /**
     * 完成页面回显
     * @param userVo
     * @return
     */
    @Override
    public Integer update(UserVo userVo) {
            
            //User user = new User();
            Long userId = userVo.getUserId();
            User user = userDao.selectByUserIdUser(userId);
            String userName = userVo.getUserName();
            Integer groupId = userVo.getGroupId();
            Integer roleId = userVo.getRoleId();
            String email = userVo.getEmail();
            String phone = userVo.getPhone();
            Integer sex = userVo.getSex();
            if (roleId != 1 && roleId != 2 && roleId != 3 && sex!=0 && sex!= 1) {
                return 0;
            }
            String phoneNumberPattern = "[0-9]{11}";
            if (!phone.matches(phoneNumberPattern)){
                return 0;
            }
            String userNamePattern = "^[\\\\u4e00-\\\\u9fa5a-zA-Z0-9]*$";
            if (!userName.matches(userNamePattern)){
                return 0;
            }
            user.setUserId(userId);
            user.setUserName(userName);
            user.setGroupId(groupId);
            user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
            user.setRoleId(roleId);
            user.setEmail(email);
            user.setPhone(phone);
            user.setSex(sex);
            user.setDelFlag(0);
            Integer updated = userDao.updateUser(user);
            return updated;



    }

    @Override
    public Integer groupUpdate(UserVo userVo) {
        User user = new User();
        Long userId = userVo.getUserId();
        Integer groupId = userVo.getGroupId();
        //String name = userDao.getgroupName(groupId);
        user.setUserId(userId);
        user.setGroupId(groupId);
        Integer update=userDao.groupUpdate(user);
        return update;
    }

    @Override
    public UserVo selectByUserId(Long userId) {
        UserVo userVo=new UserVo();
        User user = userDao.selectByUserIdUser(userId);
        //if (user==null){

            //Long userid = user.getUserId();
            String username = user.getUserName();
            //根据userId查询groupId
            Integer groupId=userDao.getGroupId(userId);
            Integer roleId = user.getRoleId();
            //根据groupId查询groupName返回
            String groupName=userDao.getgroupName(groupId);
            //根据roleId查询roleName返回//无
            String email = user.getEmail();
            String phone = user.getPhone();
            Integer sex = user.getSex();

            if(roleId == 1){
                userVo.setRoleName("超级管理员");
            } else if (roleId == 2) {
                userVo.setRoleName("管理员");

            }else if(roleId==3){
                userVo.setRoleName("用户");
            }/*else {
            throw new RuntimeException("找不到该用户类别");
            }*/
            userVo.setUserName(username);
            userVo.setGroupId(groupId);
            userVo.setGroupName(groupName);
            userVo.setRoleId(roleId);
//      userVo.setRoleName();nonono
            userVo.setEmail(email);
            userVo.setPhone(phone);
            userVo.setSex(sex);
            userVo.setUserId(userId);
            userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
            userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
            return userVo;


    }

    @Override
    public Integer delete(Integer userId) {
        UserLogoutDao userLogoutDao = new UserLogoutDao();
        UserLogout userLogout = new UserLogout();
        userLogout.setUserId(userId);
        userLogout.setAdminId(2);
        userLogout.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        Integer deleteByUserId = userDao.deleteByUserId(userId);
        if (deleteByUserId>0){
            userLogoutDao.insert(userLogout);
        }
        return deleteByUserId;
    }

    @Override
    public List<UserVo> selectAll(Integer cur, Integer szie) {
        List<UserVo> userVos = new ArrayList<>();
        List<User> users = userDao.selectAll(cur,szie);
        //if (!users.isEmpty()){

            for (User user: users){
                UserVo userVo = new UserVo();
                Long userId = user.getUserId();
                String userName = user.getUserName();
                String groupName = userDao.getgroupName(user.getGroupId());
                Integer roleId = user.getRoleId();
                String email = user.getEmail();
                String phone = user.getPhone();
                Integer sex = user.getSex();
                userVo.setUserName(userName);
                userVo.setGroupId(user.getGroupId());
                userVo.setGroupName(groupName);
                userVo.setRoleId(roleId);
                if(roleId == 1){
                    userVo.setRoleName("超级管理员");
                } else if (roleId == 2) {
                    userVo.setRoleName("管理员");

                }else if(roleId==3){
                    userVo.setRoleName("用户");
                }/*else {
            throw new RuntimeException("找不到该用户类别");
            }*/
                userVo.setEmail(email);
                userVo.setPhone(phone);
                userVo.setSex(sex);
                userVo.setUserId(userId);
                userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                userVos.add(userVo);
            }

            return userVos;
        //}
        //return null;
    }

    @Override
    public List<UserVo> selectByRoleId(Integer roleId, Integer cur, Integer szie) {
        ArrayList<UserVo> userVos = new ArrayList<>();
        ArrayList<User> users=userDao.selectByRoleIdUsers(roleId,cur,szie);
        //if (!users.isEmpty()){

            for (User user: users){
                UserVo userVo = new UserVo();
                Long userId = user.getUserId();
                String userName = user.getUserName();
                Integer groupId = user.getGroupId();
                //Integer groupId = userDao.getGroupId(userId);
                String groupName = userDao.getgroupName(groupId);
                //roleId = user.getRoleId();
                String email = user.getEmail();
                String phone = user.getPhone();
                Integer sex = user.getSex();
                userVo.setUserName(userName);
                userVo.setGroupId(groupId);
                userVo.setGroupName(groupName);
                userVo.setRoleId(roleId);
                if(roleId == 1){
                    userVo.setRoleName("超级管理员");
                } else if (roleId == 2) {
                    userVo.setRoleName("管理员");

                }else if(roleId==3){
                    userVo.setRoleName("用户");
                }/*else {
            throw new RuntimeException("找不到该用户类别");
            }*/
                userVo.setEmail(email);
                userVo.setPhone(phone);
                userVo.setSex(sex);
                userVo.setUserId(userId);
                userVo.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                userVo.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                userVos.add(userVo);
            }
            return userVos;
        //}
        //return null;
    }

    @Override
    public Integer save(UserVo userVo) {
        //Date date = new Date();
        User user = new User();
        String userName = userVo.getUserName();
        Integer groupId = userVo.getGroupId();
        Integer roleId = userVo.getRoleId();
        String email = userVo.getEmail();
        String phone = userVo.getPhone();
        Integer sex = userVo.getSex();
        String password = userVo.getPassword();
        if (roleId != 1 && roleId != 2 && roleId != 3 && sex!=0 && sex!= 1) {
            return 0;
        }
        String phoneNumberPattern = "[0-9]{11}";
        if (!phone.matches(phoneNumberPattern)){
            return 0;
        }
        String userNamePattern = "^[\\\\u4e00-\\\\u9fa5a-zA-Z0-9]*$";
        if (!userName.matches(userNamePattern)){
            return 0;
        }

        user.setUserName(userName);
        user.setGroupId(groupId);
        user.setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setCreateTime(new java.sql.Date(System.currentTimeMillis()));
        user.setRoleId(roleId);
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setDelFlag(0);
        user.setPassword(password);
        Integer updated = userDao.insertUser(user);
        return updated;
    }
}
