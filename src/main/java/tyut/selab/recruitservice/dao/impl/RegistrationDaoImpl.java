package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.BaseDao;
import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    BaseDao baseDao = new BaseDao();
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
        String sql = """
                select id,
                interview_id userId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time createTime,
                update_time updateTime,
                form registration_form
                where userId = ?
                """;
        List<RegistrationForm> revList = baseDao.baseQuery(RegistrationForm.class, sql, registrationId);

        return revList != null && revList.size() > 0 ? revList.get(0):null;
    }

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    public RegistrationForm selectByIntervieweesName(String intervieweesName){
        String sql = """
                select id,
                interview_id userId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time createTime,
                update_time updateTime,
                form registration_form
                where userId = select user_id form sys_user where user_name = ?
                """;
        List<RegistrationForm> registrationForm = baseDao.baseQuery(RegistrationForm.class, sql, intervieweesName);
        return registrationForm != null && registrationForm.size() > 0 ? registrationForm.get(0):null;
    }

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    @Override
    public List<RegistrationForm> selectByIntervieweesNameList(String intervieweesName) {
        String sql = """
                select id,
                interview_id userId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time createTime,
                update_time updateTime,
                form registration_form
                where userId = select user_id form sys_user where user_name = ?
                """;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class, sql, "%" + intervieweesName + "%");
        return registrationForms != null && registrationForms.size() > 0 ? registrationForms:null;
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
        String sql = """
                select id,
                interview_id userId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time createTime,
                update_time updateTime,
                form registration_form
                where intentDepartment= ?
                """;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class, sql,intentDepartment);
        return registrationForms != null && registrationForms.size() > 0 ? registrationForms:null;
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
