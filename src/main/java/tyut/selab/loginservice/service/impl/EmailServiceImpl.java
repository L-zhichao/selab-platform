package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.service.EmailService;

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
    public Integer save(Email email) {
        emailDao.

        return null;
    }

    @Override
    public Integer update(Email email) {
        return null;
    }

    @Override
    public Integer queryNumForSameEmail(String email) {
        return null;
    }
}
