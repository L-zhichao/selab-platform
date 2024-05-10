package tyut.selab.recruitservice.service.impl;

import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.service.RegistrationService;
import tyut.selab.recruitservice.view.RegistrationVo;

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
    public List<RegistrationVo> selectList(Integer cur,Integer size){
        return null;
    }

    /**
     *   通过 registrationId 查询报名表信息
     * @param registrationId 报名表id
     * @return
     */
    public RegistrationVo selectRegistrationById(Integer registrationId){
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

    /**
     *  通过意向部门查询报名表
     * @return
     */
    public List<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size){
        return null;
    }

    /**
     *  通过年级查询报名表
     * @param grade
     * @return
     */
    public List<RegistrationVo> selectByGradeId(Integer grade, Integer cur, Integer size){
        return null;
    }

    /**
     *  查询本人提交报名表
     * @return
     */
    public RegistrationVo queryMyRecruit(Integer userId){
        return null;
    }

}
