package tyut.selab.recruitservice.service;

import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

public interface RegistrationService {

    /**
     *  增加报名表
     * @param registrationDto
     * @return
     */

    public Integer insertRegistration(RegistrationDto registrationDto);

    /**
     *   修改报名表信息(管理员操作)
     * @param registrationVo
     * @return
     */
    public Integer updateRegistration(RegistrationVo registrationVo);

    /**
     *  分页查询所有报名表
     * @return
     */
    public List<RegistrationVo> selectList(Integer cur,Integer size);

    /**
     *   通过 registrationId 查询报名表信息
     * @param registrationId 报名表id
     * @return
     */
    public RegistrationVo selectRegistrationById(Integer registrationId);

    /**
     *   通过面试者Name查询报名表信息(支持模糊查询)
     * @param intervieweesName
     * @return
     */
    public List<RegistrationVo> selectByIntervieweesName(Integer cur, Integer size,String intervieweesName);

    /**
     *  通过意向部门查询报名表
     * @return
     */
    List<RegistrationVo> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size);

    /**
     *  通过年级查询报名表
     * @param grade
     * @return
     */
    List<RegistrationVo> selectByGradeId(Integer grade,Integer cur,Integer size);

    /**
     *  查询本人提交报名表
     * @return
     */
    RegistrationVo queryMyRecruit(Integer userId);

}
