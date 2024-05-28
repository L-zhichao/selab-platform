package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;

import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    /**
     *  增加一个报名表
     * @param registrationForm
     * @return
     */
    public Integer insert(RegistrationForm registrationForm){
        return null;
    }

    /**
     *  修改报名表信息
     * @param registrationForm
     * @return
     */
    public Integer update(RegistrationForm registrationForm){
        return null;
    }

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    public RegistrationForm selectByRegistrationId(Integer registrationId){
        return null;
    }

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    public RegistrationForm selectByIntervieweesName(String intervieweesName){
        return null;
    }

    /**
     * 查询所有报名表
     * @return
     */
    public List<RegistrationForm> selectAll(Integer cur, Integer size){
        return null;
    }

    /**
     *  通过意向部门查询报名表
     * @return
     */
    public List<RegistrationForm> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size){
        return null;
    }

    /**
     *  通过年级查询报名表
     * @param grade
     * @return
     */
    public List<RegistrationForm> selectByGradeId(Integer grade, Integer cur, Integer size){
        return null;
    }
}
