package tyut.selab.recruitservice.dao;

import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.view.RegistrationVo;
import tyut.selab.utils.PageUtil;

import java.util.List;

public interface RegistrationDao {

    /**
     *  增加一个报名表
     * @param registrationForm
     * @return
     */
    Integer insert(RegistrationForm registrationForm);

    /**
     *  修改报名表信息
     * @param registrationForm
     * @return
     */
    Integer update(RegistrationForm registrationForm);

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    RegistrationForm selectByRegistrationId(Integer registrationId);

    /**
     * 通过面试者姓名查询报名表
     *
     * @param intervieweesName
     * @return
     */
    PageUtil<RegistrationForm> selectByIntervieweesName(String intervieweesName,Integer cur,Integer size);

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     */
    List<RegistrationForm> selectList(String intervieweesName);

    /**
     * 查询所有报名表
     * @return
     */
    PageUtil<RegistrationForm> selectAll(Integer cur, Integer size);

    /**
     *  通过意向部门查询报名表
     * @return
     */
    PageUtil<RegistrationForm> selectByIntentDepartment(Integer intentDepartment,Integer cur,Integer size);

    /**
     *  通过年级查询报名表
     * @param grade
     * @return
     */
    PageUtil<RegistrationForm> selectByGradeId(Integer grade,Integer cur,Integer size);


}
