package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.service.EmailService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @className: EmailServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:38
 * @version: 1.0
 */
public class EmailServiceImpl implements EmailService {
    EmailDaoImpl emailDao = new EmailDaoImpl();
    @Override
    public Integer save(Email email) throws SQLException {
        int result = emailDao.insert(email);
        return result;
    }
    @Override
    public Integer update(Email email) throws SQLException {
        Integer i = emailDao.update(email);
        return i;
    }
    @Override
    public Integer queryNumForSameEmail(String email) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Integer countOfEmail = emailDao.selectNumForSameEmail(email);
        return countOfEmail;
    }

    @Override
    public Integer getEmailNum() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return emailDao.getEmailNum();
    }

}
