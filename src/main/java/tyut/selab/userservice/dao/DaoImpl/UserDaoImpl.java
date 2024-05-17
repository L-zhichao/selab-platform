package tyut.selab.userservice.dao.DaoImpl;

import org.springframework.jdbc.core.JdbcTemplate;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.domain.User;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.JDBCUtils;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements UserDao {

    //JdbcTemplate连接数据、库释放资源
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Integer insertUser() {
        return null;
    }


    /**
     * Description: 修改用户信息
     * @param user
     * @return Integer
     */
    @Override
    public Integer updateUser(User user) {
        //动态sql or 数据回显 怎么写啊怎么啊？？？

        //判断roleId 管理员修改所用，用户仅自己
        if (user.getUserId().equals(1) || user.getUserId().equals(2)) {
            String sql = "UPDATE sys_user SET user_name=?," +
                    "group_id=?,group_name=?," +
                    "email=?,phone=?,sex=?,where user_id=?";
            //调用JdbcTemplate方法，执行sql
            int rows = template.update(sql, 1,1,1,1,1,1,8);
            return rows;
        }
        if (user.getUserId().equals(3)) {
            String sql = "UPDATE sys_user SET user_name=?," +
                    "group_id=?,group_name=?" +
                    "email=?,phone=?,sex=?,where userid=?";
            int rows = template.update(sql, 1,1,1,1,1,1,1, user.getUserId());
            return rows;
        }
        return null;
    }

    /**
    * Description: 修改用户权限
    * @param user
    * @return Integer
    */

    @Override
    public Integer updateUserRole(User user) {
        String sql = "UPDATE sys_user SET role_id=? where user_id=?";
        int rows = template.update(sql,1,user.getUserId());
        return rows;
    }


    @Override
    public User selectByUserIdUser(Integer userId) {
        return null;
    }

    @Override
    public List<User> selectByGroupIdUsers(Integer groupId) {
        return null;
    }

    @Override
    public User selectByUserName(String userName) {
        return null;
    }

    /**
    * Description: 注销用户
    * @param userId
    * @return Integer
    */
    @Override
    public Integer deleteByUserId(Integer userId) {
        String sql = "DELETE FROM sys_user WHERE user_id=?";
        int rows = template.update(sql,userId);
        return rows;
    }

    /**
    * Description: 保存注销记录
    * @param userId
    * @return Integer
    */

}
