package tyut.selab.recruitservice.dao.impl;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.recruitservice.service.QueryMyException;
import tyut.selab.utils.JDBCTools;
import tyut.selab.loginservice.utils.SecurityUtil;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import tyut.selab.utils.PageUtil;
import tyut.selab.recruitservice.dao.BaseDao;

import javax.xml.crypto.Data;
import java.util.List;
public class RegistrationDaoImpl implements RegistrationDao {
    BaseDao baseDao = new BaseDao();
    /**
     *  增加一个报名表
     * @param registrationForm
     * @return
     */
    public Integer insert(RegistrationForm registrationForm) {
        try {
            Connection connection = JDBCTools.getConnection();
            registrationForm.setIntervieweesId(123456);
            /*registrationForm.setIntervieweesId(getUser());*/
            String sql = """
                    INSERT INTO
                    selab_platform.registration_form
                    (interview_id,
                    email,
                    phone,
                    intent_department,
                    grade,
                    classroom,
                    interview_time,
                    introduce,
                    purpose,
                    remark,
                    init_time,
                    update_time)
                    VALUES(?,?,?,?,?,?,?,?,?,?,?,?)
                    """;
            SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date1 = s1.format(registrationForm.getInterviewTime());

            int rows = baseDao.baseUpdate(sql,
                    1,
                    registrationForm.getEmail(),
                    String.valueOf(registrationForm.getPhone()),
                    registrationForm.getIntentDepartment(),
                    registrationForm.getGrade(),
                    registrationForm.getClassroom(),
                    date1,
                    registrationForm.getIntroduce(),
                    registrationForm.getPurpose(),
                    registrationForm.getRemark(),
                    new Date(),
                    new Date()
            );
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, registrationForm.getEmail());
//            preparedStatement.setString(2, String.valueOf(registrationForm.getPhone()));
//            preparedStatement.setInt(3, registrationForm.getIntentDepartment());
//            preparedStatement.setInt(4, registrationForm.getGrade());
//            preparedStatement.setString(5, registrationForm.getClassroom());
//            SimpleDateFormat s1 = new SimpleDateFormat("hh:mm:ss");
//            String date1 = s1.format(registrationForm.getInterviewTime());
//            preparedStatement.setString(6, date1);
//            preparedStatement.setString(7, registrationForm.getIntroduce());
//            preparedStatement.setString(8, registrationForm.getPurpose());
//            preparedStatement.setString(9, registrationForm.getRemark());
//            DateTimeFormatter s2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            registrationForm.setInitTime(new Date());
//            String date2 = s2.format(registrationForm.getInitTime());
//            preparedStatement.setString(10, date2);
//            SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            registrationForm.setUpdateTime(new Date());
//            String date3 = s3.format(registrationForm.getUpdateTime());
//            preparedStatement.setString(11, date3);
//            preparedStatement.setInt(12, registrationForm.getIntervieweesId());
//            int rows = preparedStatement.executeUpdate();
//            System.out.println(registrationForm);
//            System.out.println(rows);
//            JDBCTools.free(connection);
//            return rows;
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  修改报名表信息
     * @param registrationForm
     * @return
     */
    public Integer update(RegistrationForm registrationForm) {
        //            Connection connection = JDBCTools.getConnection();
//            registrationForm.setIntervieweesId(123456);
//            /*registrationForm.setIntervieweesId(getUser());*/
//            String sql = "UPDATE registration_form SET interview_id = ?,email = ?,phone = ?,intent_department = ?,grade = ?,classroom = ?,interview_time = ?,introduce = ?,purpose = ?,remark = ?,init_time = ?,update_time = ? WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, registrationForm.getIntervieweesId());
//            preparedStatement.setString(2, registrationForm.getEmail());
//            preparedStatement.setString(3, String.valueOf(registrationForm.getPhone()));
//            preparedStatement.setInt(4, registrationForm.getId());
//            preparedStatement.setInt(5, registrationForm.getId());
//            preparedStatement.setString(6, registrationForm.getClassroom());
//            SimpleDateFormat s1 = new SimpleDateFormat("hh:mm:ss");
//            String date1 = s1.format(registrationForm.getInterviewTime());
//            preparedStatement.setString(7, date1);
//            preparedStatement.setString(8, registrationForm.getIntroduce());
//            preparedStatement.setString(9, registrationForm.getPurpose());
//            preparedStatement.setString(10, registrationForm.getRemark());
//            DateTimeFormatter s2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            registrationForm.setInitTime(new Date());
//            String date2 = s2.format(registrationForm.getInitTime());
//            preparedStatement.setString(11, date2);
//            SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            registrationForm.setUpdateTime(new Date());
//            String date3 = s3.format(registrationForm.getUpdateTime());
//            preparedStatement.setString(12, date3);
//            preparedStatement.setInt(13, registrationForm.getId());
//            int rows = preparedStatement.executeUpdate();
//            System.out.println(rows);
//            JDBCTools.free(connection);
        String sql = """
                update
                selab_platform.registration_form
                set
                 interview_id = ?,
                 email = ?,
                 phone = ?,
                 intent_department = ?,
                 grade = ?,
                 classroom = ?,
                 interview_time = ?,
                 introduce = ?,
                 purpose = ?,
                 remark = ?,
                 update_time = ?
             where id = ?
                """;
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = s1.format(registrationForm.getInterviewTime());

        int rows = baseDao.baseUpdate(sql,
                4,
                registrationForm.getEmail(),
                String.valueOf(registrationForm.getPhone()),
                registrationForm.getIntentDepartment(),
                registrationForm.getGrade(),
                registrationForm.getClassroom(),
                date1,
                registrationForm.getIntroduce(),
                registrationForm.getPurpose(),
                registrationForm.getRemark(),
                new Date(),
                registrationForm.getId()
        );
        return rows;
    }


    /**
     * 查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    @Override
    public List<RegistrationForm> selectList(String intervieweesName) {
        return null;
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

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    @Override
    public RegistrationForm selectByRegistrationId(Integer registrationId) {
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
}
