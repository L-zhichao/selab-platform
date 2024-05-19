package tyut.selab.loginservice.dao.impl;

import tyut.selab.loginservice.dao.EmailDao;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.utils.BaseDao;

public class EmailDaoImpl extends BaseDao implements EmailDao {
    @Override
    public Integer insert(Email email) {
        String sql = "insert into email user_id , email , create_time";
        executeUpdate();
        return null;
    }

    @Override
    public Integer update(Email email) {
        return null;
    }

    @Override
    public Integer selectNumForSameEmail(String email) {
        return null;
    }

    @Override
    public Integer delete(Integer userId) {
        return null;
    }
}
