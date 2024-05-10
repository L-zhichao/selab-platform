package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {


    @Override
    public Integer insert(RegistrationForm registrationForm) {
        return null;
    }

    @Override
    public Integer update(RegistrationForm registrationForm) {
        return null;
    }

    @Override
    public RegistrationForm selectByRegistrationId(Integer registrationId) {
        return null;
    }

    @Override
    public RegistrationForm selectByIntervieweesName(String intervieweesName) {
        return null;
    }


    @Override
    public List<RegistrationForm> selectAll(Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<RegistrationForm> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<RegistrationForm> selectByGradeId(Integer grade, Integer cur, Integer size) {
        return null;
    }


}
