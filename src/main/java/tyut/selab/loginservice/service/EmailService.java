package tyut.selab.loginservice.service;

import tyut.selab.loginservice.domain.Email;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface EmailService {

    /**
     *  添加邮箱信息
     * @param email
     * @return
     */
    public Integer save(Email email) throws SQLException;

    /**
     *  修改邮箱信息
     * @param email
     * @return
     */
    public Integer update(Email email) throws SQLException;

    /**
     *  查询同一个邮箱注册用户数量
     * @param email
     * @return
     */
    public Integer queryNumForSameEmail(String email) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
    public Integer getEmailNum() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;



}
