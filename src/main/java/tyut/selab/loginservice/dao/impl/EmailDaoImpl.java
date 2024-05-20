package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.common.Constant;
import tyut.selab.loginservice.dao.EmailDao;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.utils.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class EmailDaoImpl extends BaseDao implements EmailDao, Constant {
    /**
     *添加邮箱数据
     * @param email
     * @return 返回修改作用的行数，如果报错的话返回0
     */
    /**
     * 删除邮箱数据
     * @param email
     * @return 返回修改作用的行数，如果报错的话返回0
     */
    @Override
    public Integer insert(Email email) {
        //添加email表中的字段为user_id,email,create_time
        String sql = "insert into email values(?,?,now())";
        int result = 0;
        try {
            result = executeUpdate(sql,email.getUserId(),email.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改Email表信息
     * @param email
     * @return
     */
    @Override
    public Integer update(Email email) {
        String sql = "update email set email = ?,create_time = now() where user_id = ?";
        int result = 0;
        try {
            result = executeUpdate(sql,email.getEmail(),email.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer selectNumForSameEmail(String email) {
        String sql = "select count(*) from email where email = ?";
        int result = 0;
        List<Integer> integer = null;
        try {
            integer = executeQuery(Integer.class, sql, email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return integer.get(0);
    }

    /**
     * 删除指定的email数据
     * @param userId
     * @return
     */
    @Override
    public Integer delete(Integer userId) {

        return null;
    }
}
