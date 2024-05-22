package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.service.impl.UserServiceImpl;
import tyut.selab.userservice.vo.UserVo;

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
        List<RegistrationForm> registrationForms = red.selectAll(cur,size);
        List<RegistrationVo> registrationVos = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        for(RegistrationForm registrationForm : registrationForms){
            UserVo userVo = userService.selectByUserId(registrationForm.getIntervieweesId());
            registrationVos.add(registrationForm.getRegistrationVo(userVo));
        }


        List<RegistrationVo> registrationVosNew = new ArrayList<>();
        for(int i = 0; i < cur ; i++){
            registrationVosNew.add(registrationVos.get((size - 1) * cur + 1 + i));
        }

        return registrationVosNew;
    }

    @Override
    public RegistrationVo selectRegistrationById(Integer registrationId) {
        RegistrationForm reg = red.selectByRegistrationId(registrationId);
        if(reg == null){
            return null;
        }
        UserServiceImpl userService = new UserServiceImpl();
        UserVo userVo = userService.selectByUserId(registrationId);
        return reg.getRegistrationVo(userVo);
    }

    @Override
    public List<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size, String intervieweesName) {
        List<RegistrationForm> registrationForms = red.selectByIntervieweesName(intervieweesName);
        List<RegistrationVo> registrationVos = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        for(RegistrationForm registrationForm : registrationForms){
            UserVo userVo = userService.selectByUserId(registrationForm.getIntervieweesId());
            registrationVos.add(registrationForm.getRegistrationVo(userVo));
        }


        List<RegistrationVo> registrationVosNew = new ArrayList<>();
        for(int i = 0; i < cur ; i++){
            registrationVosNew.add(registrationVos.get((size - 1) * cur + 1 + i));
        }

        return registrationVosNew;
    }



    @Override
    public List<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) {
        List<RegistrationForm> registrationForms = red.selectByIntentDepartment(intentDepartment, cur, size);
        List<RegistrationVo> registrationVos = null;
        UserService userService = new UserServiceImpl();
        for(RegistrationForm registrationForm : registrationForms){
            UserVo userVo = userService.selectByUserId(registrationForm.getIntervieweesId());
            registrationVos.add(registrationForm.getRegistrationVo(userVo));
        }
        List<RegistrationVo> registrationVosNew = new ArrayList<>();
        for(int i = 0; i < cur ; i++){
            registrationVosNew.add(registrationVos.get((size - 1) * cur + 1 + i));
        }

        return registrationVosNew;
    }

    @Override
    public List<RegistrationVo> selectByGradeId(Integer grade, Integer cur, Integer size) {
        List<RegistrationForm> registrationForms = red.selectByGradeId(grade, cur, size);
        List<RegistrationVo> registrationVos = null;
        UserService userService = new UserServiceImpl();
        for(RegistrationForm registrationForm : registrationForms){
            UserVo userVo = userService.selectByUserId(registrationForm.getIntervieweesId());
            registrationVos.add(registrationForm.getRegistrationVo(userVo));
        }
        List<RegistrationVo> registrationVosNew = new ArrayList<>();
        for(int i = 0; i < cur ; i++){
            registrationVosNew.add(registrationVos.get((size - 1) * cur + 1 + i));
        }

        return registrationVosNew;
    }

    @Override
    public RegistrationVo queryMyRecruit(Integer userId) {
        return null;
    }
}
