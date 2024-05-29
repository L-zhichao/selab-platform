package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.InsertException;
import tyut.selab.recruitservice.service.QueryMyException;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.service.UpdateException;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

import static tyut.selab.recruitservice.domain.RegistrationForm.toVo;

public class RegistrationServiceImpl implements RegistrationService {
    private RegistrationDao registrationDao = new RegistrationDaoImpl();
    public void setDao(RegistrationDao registrationDao){
        this.registrationDao = registrationDao;
    }
    private RegistrationDto registrationDto;

    @Override
    public Integer insertRegistration(RegistrationDto registrationDto)throws InsertException{
        Integer rows = registrationDao.insert(RegistrationForm.fromDto(registrationDto));
        if(rows>=1){
            return rows;
        }
        else{
            throw new InsertException();
        }
    }

    @Override
    public Integer updateRegistration(RegistrationVo registrationVo) throws UpdateException {
        Integer rows = registrationDao.update(RegistrationForm.fromVo(registrationVo));
        if(rows>=1){
            return rows;
        }
        else{
            throw new UpdateException();
        }
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
    public RegistrationVo queryMyRecruit(Integer userId) throws QueryMyException {
        if (registrationDao.selectByUserId(userId).getIntervieweesId() == null) {
            throw new QueryMyException();
        } else {
                RegistrationVo registrationVo = toVo(registrationDao.selectByUserId(userId));
                return registrationVo;
            }
        }
    }