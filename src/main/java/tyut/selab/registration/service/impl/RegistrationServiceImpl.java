package tyut.selab.registration.service.impl;

import tyut.selab.registration.dto.RegistrationDto;
import tyut.selab.registration.service.RegistrationService;
import tyut.selab.registration.view.RegistrationVo;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    /**
     *  增加报名表
     * @param registrationDto
     * @return
     */

    public Integer insertRegistration(RegistrationDto registrationDto){
        return null;
    }

    /**
     *   修改报名表信息(管理员操作)
     * @param registrationDto
     * @return
     */
    public Integer updateRegistration(RegistrationDto registrationDto){
        return null;
    }

    /**
     *  分页查询所有报名表
     * @return
     */
    public List<RegistrationDto> selectList(Integer cur,Integer size){
        return null;
    }

    /**
     *   通过 registrationId 查询报名表信息
     * @param registrationId 报名表id
     * @return
     */
    public RegistrationDto selectRegistrationById(Integer registrationId){
        return null;
    }

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * @param intervieweesName
     * @return
     */
    public RegistrationVo selectByIntervieweesName(String intervieweesName){
        return null;
    }
}
