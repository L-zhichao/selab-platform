package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.*;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.userservice.service.UserService;
import tyut.selab.userservice.service.impl.UserServiceImpl;
import tyut.selab.userservice.vo.UserVo;
import tyut.selab.utils.PageUtil;

import java.util.ArrayList;
import java.util.List;

import static tyut.selab.recruitservice.domain.RegistrationForm.toVo;

/**
 * @author 21017
 */
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
    public PageUtil<RegistrationVo> selectList(Integer cur, Integer size) throws ServiceException {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectAll(cur,size);
        if(registrationFormPageUtil == null){
            throw new ServiceException();
        }
        if(registrationFormPageUtil.getData() == null){
            throw new ServiceException();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public RegistrationVo selectRegistrationById(Integer registrationId) throws ServiceException {
        RegistrationForm registrationForm = registrationDao.selectByRegistrationId(registrationId);
        if(registrationForm == null || registrationForm.getId() == 0){
            throw new ServiceException();
        }
        UserServiceImpl userService = new UserServiceImpl();
        UserVo userVo = userService.selectByUserId(registrationId);
        return getRegistrationVo(userVo,registrationForm);
    }

    @Override
    public PageUtil<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size, String intervieweesName) throws ServiceException {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByIntervieweesName(intervieweesName,cur,size);
        if(registrationFormPageUtil == null){
            throw new ServiceException();
        }
        if(registrationFormPageUtil.getData() == null){
            throw new ServiceException();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }



    @Override
    public PageUtil<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size) throws ServiceException {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByIntentDepartment(intentDepartment, cur, size);
        if(registrationFormPageUtil == null){
            throw new ServiceException();
        }
        if(registrationFormPageUtil.getData() == null){
            throw new ServiceException();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public PageUtil<RegistrationVo> selectByGradeId(Integer grade, Integer cur, Integer size) throws ServiceException {
        PageUtil<RegistrationForm> registrationFormPageUtil = registrationDao.selectByGradeId(grade, cur, size);
        if(registrationFormPageUtil == null){
            throw new ServiceException();
        }
        if(registrationFormPageUtil.getData() == null){
            throw new ServiceException();
        }
        return toPageUtilOfRegistrationVos(registrationFormPageUtil);
    }

    @Override
    public RegistrationVo queryMyRecruit(Integer userId) throws QueryMyException {
        RegistrationForm registrationForm = registrationDao.selectByRegistrationId(userId);
        if(registrationForm == null || registrationForm.getIntervieweesId() == null){
            throw new QueryMyException();
        }else {
                RegistrationVo registrationVo = toVo(registrationDao.selectByRegistrationId(userId));
                return registrationVo;
          }
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



