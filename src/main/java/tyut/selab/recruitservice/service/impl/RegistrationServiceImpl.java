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
import tyut.selab.utils.PageUtil;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    RegistrationDao registrationDao = new RegistrationDaoImpl();

    @Override
    public Integer insertRegistration(RegistrationDto registrationDto) {
        return null;
    }

    @Override
    public Integer updateRegistration(RegistrationVo registrationVo) {
        return null;
    }


    private PageUtil<RegistrationVo> toPageUtilOfRegistrationVos(PageUtil<RegistrationForm> pageUtil){
        List<RegistrationVo> registrationVos = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        for(RegistrationForm registrationForm : pageUtil.getData()){
            UserVo userVo = userService.selectByUserId(registrationForm.getIntervieweesId());
            registrationVos.add(getRegistrationVo(userVo,registrationForm));
        }
        PageUtil<RegistrationVo> registrationVoPageUtil = new PageUtil<>();
        registrationVoPageUtil.setSize(pageUtil.getSize());
        registrationVoPageUtil.setTotal(pageUtil.getTotal());
        registrationVoPageUtil.setCur(pageUtil.getCur());
        registrationVoPageUtil.setData(registrationVos);
        return registrationVoPageUtil;
    }
    @Override
    public PageUtil<RegistrationVo> selectList(Integer cur, Integer size) {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectAll(cur,size);
        if(registrationFormPageUtil == null){
            return new PageUtil<>();
        }

        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public RegistrationVo selectRegistrationById(Integer registrationId) {
        RegistrationForm registrationForm = registrationDao.selectByRegistrationId(registrationId);
        if(registrationForm == null){
            return new RegistrationVo();
        }
        UserServiceImpl userService = new UserServiceImpl();
        UserVo userVo = userService.selectByUserId(registrationId);
        return getRegistrationVo(userVo,registrationForm);
    }

    @Override
    public PageUtil<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size, String intervieweesName) {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByIntervieweesName(intervieweesName,cur,size);
        if(registrationFormPageUtil == null){
            return new PageUtil<>();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }



    @Override
    public PageUtil<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByIntentDepartment(intentDepartment, cur, size);
        if(registrationFormPageUtil == null){
            return new PageUtil<>();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public PageUtil<RegistrationVo> selectByGradeId(Integer grade, Integer cur, Integer size) {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByGradeId(grade, cur, size);
        if(registrationFormPageUtil == null){
            return new PageUtil<>();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public RegistrationVo queryMyRecruit(Integer userId) {
        return null;
    }

    public RegistrationVo getRegistrationVo(UserVo userVo,RegistrationForm registrationForm){
        return new RegistrationVo(
                registrationForm.getId(),
                userVo,
                registrationForm.getEmail(),
                registrationForm.getPhone(),
                registrationForm.getIntentDepartment(),
                registrationForm.getClassroom(),
                registrationForm.getInterviewTime(),
                registrationForm.getIntroduce(),
                registrationForm.getPurpose(),
                registrationForm.getRemark()
        );
    }
}
