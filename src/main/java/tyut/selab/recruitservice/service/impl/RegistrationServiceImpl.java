package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    RegistrationDao red = new RegistrationDaoImpl();

    @Override
    public Integer insertRegistration(RegistrationDto registrationDto) {
        return null;
    }

    @Override
    public Integer updateRegistration(RegistrationVo registrationVo) {
        return null;
    }


    @Override
    public List<RegistrationVo> selectList(Integer cur, Integer size) {
        return null;
    }

    @Override
    public RegistrationVo selectRegistrationById(Integer registrationId) {
        RegistrationForm reg = red.selectByRegistrationId(registrationId);
        return reg.getRegistrationVo(userVo);
    }

    @Override
    public List<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size, String intervieweesName) {
        RegistrationForm reg = red.selectByIntervieweesName(intervieweesName);
        List<RegistrationVo> regList = new ArrayList<>();
        if(reg != null){
            //这俩地方记得转换成RegistrationVo类型
            regList.add(reg);
        }else{
            List<RegistrationForm> registrationForms = red.selectByIntervieweesNameList(intervieweesName);
            regList.add(registrationForms);
        }
        return regList;
    }



    @Override
    public List<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) {
        List<RegistrationForm> registrationForms = red.selectByIntentDepartment(intentDepartment, cur, size);
        List<RegistrationVo> registrationVos = null;
        for(RegistrationForm registrationForm : registrationForms){
            registrationVos.add()
        }
        return registrationVos;
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
