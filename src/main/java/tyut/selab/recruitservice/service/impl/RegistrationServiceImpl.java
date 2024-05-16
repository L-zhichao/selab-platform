package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    private RegistrationDao registrationDao;
    public void setDao(RegistrationDao registrationDao){
        this.registrationDao = registrationDao;
    }
    private RegistrationDto registrationDto;

    @Override
    public Integer insertRegistration(RegistrationDto registrationDto){
        registrationDao.insert(RegistrationForm.fromDto(registrationDto));
        return null;
    }

    @Override
    public Integer updateRegistration(RegistrationVo registrationVo) {
        registrationDao.update(RegistrationForm.fromVo(registrationVo));
        return null;
    }


    @Override
    public List<RegistrationVo> selectList(Integer cur, Integer size) {
        return null;
    }

    @Override
    public RegistrationVo selectRegistrationById(Integer registrationId) {
        return null;
    }

    @Override
    public List<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size, String intervieweesName) {
        return null;
    }



    @Override
    public List<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<RegistrationVo> selectByGradeId(Integer grade, Integer cur, Integer size) {
        return null;
    }

    @Override
    public RegistrationVo queryMyRecruit(Integer userId) {

        return null;
    }
}
