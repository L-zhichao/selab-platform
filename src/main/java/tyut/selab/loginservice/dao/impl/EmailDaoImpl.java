package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.common.Constant;
import tyut.selab.loginservice.dao.EmailDao;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.utils.BaseDao;

import java.sql.SQLException;

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
        String sql = "insert into email values(null,?,? )";
        int result = 0;
        try {
            result = executeUpdate(sql,email.getEmail(),email.getCreateTime(),email.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer update(Email email) {
        String sql = "update email set email = ?,create_time = ? where user_id = ?";
        int result = 0;
        try {
            result = executeUpdate(sql,email.getUserId(),email.getEmail(),email.getCreateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer selectNumForSameEmail(String email) {
        String sql = "select count(*) from email where email = ?";
        int result = 0;
        try {
            result = executeQueryOne(Integer.class,sql,email);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除指定的email数据
     * @param userId
     * @return
     */
    @Override
    public Integer delete(Integer userId) {
        //由于我们的user_id是主键递增的，所以我们只要删除email信息的话，就需要重新截断进行存储
        return null;
    }
}
