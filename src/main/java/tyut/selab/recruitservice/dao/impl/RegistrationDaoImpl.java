package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.BaseDao;
import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.utils.PageUtil;

import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    BaseDao baseDao = new BaseDao();
    /**
     *  增加一个报名表
     * @param registrationForm
     * @return
     */
    @Override
    public Integer insert(RegistrationForm registrationForm){
        return null;
    }

    /**
     *  修改报名表信息
     * @param registrationForm
     * @return
     */
    @Override
    public Integer update(RegistrationForm registrationForm){
        return null;
    }

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    @Override
    public RegistrationForm selectByRegistrationId(Integer registrationId){
        String sql = """
                select id,
                interview_id as IntervieweesId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time initTime,
                update_time updateTime
                from selab_platform.registration_form
                where interview_id = ? and
                selab_platform.registration_form.del_flag <> 1
                """;
        List<RegistrationForm> revList = baseDao.baseQuery(RegistrationForm.class, sql, registrationId);
        return revList != null && revList.size() > 0 ? revList.get(0):null;
    }

    /**
     *  通过面试者姓名查询报名表
     * @param intervieweesName
     * @return
     */
    @Override
    public PageUtil<RegistrationForm> selectByIntervieweesName(String intervieweesName,Integer cur,Integer size){
        String sql = """
              select selab_platform.registration_form.id,
                     selab_platform.registration_form.interview_id IntervieweesId,
                     selab_platform.registration_form.email,
                     selab_platform.registration_form.phone,
                     selab_platform.registration_form.intent_department intentDepartment,
                     selab_platform.registration_form.grade,
                     selab_platform.registration_form.classroom,
                     selab_platform.registration_form.interview_time interviewTime,
                     selab_platform.registration_form.introduce,
                     selab_platform.registration_form.purpose,
                     selab_platform.registration_form.remark,
                     selab_platform.registration_form.init_time initTime,
                     selab_platform.registration_form.update_time updateTime
              from   selab_platform.registration_form,
                     selab_platform.sys_user
              where  selab_platform.registration_form.interview_id = selab_platform.sys_user.user_id and
                     selab_platform.sys_user.user_name like '%s' and
                     selab_platform.registration_form.del_flag <> 1
              limit ?,?
                """;
        sql = String.format(sql, "%"+intervieweesName+"%");
        Integer index = (cur - 1) * size;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class,sql,index,size);;
        String sql2 = """
                select count(*)
                from selab_platform.registration_form,
                     selab_platform.sys_user
                where selab_platform.registration_form.interview_id = selab_platform.sys_user.user_id and
                     selab_platform.sys_user.user_name like '%s' and
                     selab_platform.registration_form.del_flag <> 1
                """;
        sql2 = String.format(sql2, "%"+intervieweesName+"%");
        Integer total = Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql2)));
        PageUtil<RegistrationForm> pageUtil = new PageUtil<>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        pageUtil.setData(registrationForms);
        pageUtil.setTotal(total);
        return registrationForms != null && !registrationForms.isEmpty() ? pageUtil:null;
    }

    /**
     * 查询面试者的报名表
     *
     * @param intervieweesName
     * @return
     */
    @Override
    public List<RegistrationForm> selectList(String intervieweesName) {
        return null;
    }

    /**
     * 查询所有报名表
     * @return
     */
    @Override
    public PageUtil<RegistrationForm> selectAll(Integer cur, Integer size){
        String sql = """
                select id,
                interview_id IntervieweesId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time initTime,
                update_time updateTime
                from selab_platform.registration_form
                where selab_platform.registration_form.del_flag <> 1
                limit ?,?
                """;
        Integer index = (cur - 1) * size;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class, sql,index,size);
        String sql2 = """
                select count(*)
                from selab_platform.registration_form
                where selab_platform.registration_form.del_flag <> 1
                """;
        Integer total = Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql2)));
        PageUtil<RegistrationForm> pageUtil = new PageUtil<>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        pageUtil.setData(registrationForms);
        pageUtil.setTotal(total);
        return registrationForms != null && !registrationForms.isEmpty() ? pageUtil:null;
    }

    /**
     *  通过意向部门查询报名表
     * @return
     */
    @Override
    public PageUtil<RegistrationForm> selectByIntentDepartment(Integer intentDepartment, Integer cur, Integer size){
        String sql = """
                select id,
                interview_id IntervieweesId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time initTime,
                update_time updateTime
                from selab_platform.registration_form
                where intent_department = ? and
                selab_platform.registration_form.del_flag <> 1
                limit ?,?
                """;
        Integer index = (cur - 1) * size;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class,sql,intentDepartment,index,size);
        String sql2 = """
                select count(*)
                from selab_platform.registration_form
                where intent_department = ? and
                selab_platform.registration_form.del_flag <> 1
                """;
        Integer total = Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql2,intentDepartment)));
        PageUtil<RegistrationForm> pageUtil = new PageUtil<>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        pageUtil.setData(registrationForms);
        pageUtil.setTotal(total);
        return registrationForms != null && !registrationForms.isEmpty() ? pageUtil:null;
    }

    /**
     *  通过年级查询报名表
     * @param grade
     * @return
     */
    @Override
    public PageUtil<RegistrationForm> selectByGradeId(Integer grade, Integer cur, Integer size){
        String sql = """
                select id,
                interview_id IntervieweesId,
                email,
                phone,
                intent_department intentDepartment,
                grade,
                classroom,
                interview_time interviewTime,
                introduce,
                purpose,
                remark,
                init_time initTime,
                update_time updateTime
                from selab_platform.registration_form
                where grade = ? and
                selab_platform.registration_form.del_flag <> 1
                limit ?,?
                """;
        Integer index = (cur - 1) * size;
        List<RegistrationForm> registrationForms = baseDao.baseQuery(RegistrationForm.class, sql,grade,index,size);
        String sql2 = """
                select count(*)
                from selab_platform.registration_form
                where grade = ? and
                selab_platform.registration_form.del_flag <> 1
                """;
        Integer total = Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql2,grade)));
        PageUtil<RegistrationForm> pageUtil = new PageUtil<>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        pageUtil.setData(registrationForms);
        pageUtil.setTotal(total);
        return registrationForms != null && !registrationForms.isEmpty() ? pageUtil:null;
    }

}
