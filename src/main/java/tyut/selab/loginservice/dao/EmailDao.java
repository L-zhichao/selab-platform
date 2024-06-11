package tyut.selab.loginservice.dao;

import tyut.selab.loginservice.domain.Email;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EmailDao {
    /**
     * 新增书籍
     * @param email
     * @return
     */
    public Integer insert(Email email) throws SQLException;

    /**
     *  修改书籍信息
     * @param email
     * @return
     */
    public Integer update(Email email) throws SQLException;

    /**
     *  查询统一邮件绑定用户数量
     * @param email
     * @return
     */
    public Integer selectNumForSameEmail(String email) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;

    /**
     *  删除邮箱信息
     * @param userId
     * @return
     */
    public Integer delete(Integer userId) throws SQLException;

    /**
     * 查询邮箱注册总数
     * @return
     */
    public Integer getEmailNum() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
