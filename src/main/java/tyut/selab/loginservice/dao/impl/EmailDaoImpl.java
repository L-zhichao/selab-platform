package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.common.Constant;
import tyut.selab.loginservice.dao.EmailDao;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
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
    public Integer insert(Email email) throws SQLException {
        //添加email表中的字段为user_id,email,create_time
        String sql = "insert into sys_email values(null,?,now())";
        int result = 0;
        result = executeUpdate(sql,email.getEmail());
        return result;
    }

    /**
     * 修改Email表信息
     * @param email
     * @return
     */
    @Override
    public Integer update(Email email) throws SQLException {
        String sql = "update sys_email set email = ?,create_time = now() where user_id = ?";
        int result = 0;
        result = executeUpdate(sql,email.getEmail(),email.getUserId());
        return result;
    }

    /**
     * 获取邮箱注册次数，如果返回0则是邮箱还没有被注册，返回其他值则为注册次数
     * @param email
     * @return 返回一个邮箱的注册次数
     */
    @Override
    public Integer selectNumForSameEmail(String email) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String sql = "select user_id userId,email,create_time createTime from sys_email where email = ?";
        int result = 0;
        List<Email> integer = null;
        integer = executeQuery(Email.class, sql, email);
        Object[] array = {};
        if(null != integer) {
            array = integer.toArray();
        }
        return array.length;
    }

    /**
     * 删除指定的email数据，删除成功返回更改行数，删除失败返回0
     * @param userId
     * @return 删除失败返回0，删除成功返回修改行数
     */
    @Override
    public Integer delete(Integer userId) throws SQLException {
        String sql = "delete from sys_email where user_id = ?";
        int rows = 0;
        rows = executeUpdate(sql,userId);
        return rows;
    }

    /**
     * 该方法获取注册邮件的总数
     * @return 返回已经注册的邮件的总数
     */
    @Override
    public Integer getEmailNum() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String sql = "select user_id userId,email,create_time createTime from sys_email";
        List<Email> emails = null;
        emails = executeQuery(Email.class, sql);
        Object[] array = {};
        if(null != emails) {
            array = emails.toArray();
        }
        return array.length;
    }
}
