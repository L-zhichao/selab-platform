package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.utils.JDBCTools;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    /**
     *  增加一个报名表
     * @param registrationForm
     * @return
     */
    public Integer insert (RegistrationForm registrationForm) {
        try {Connection connection = JDBCTools.getConnection() ;
            registrationForm.setIntervieweesId(123456);
            String sql = "INSERT INTO registration_form(email,phone,intent_department,grade,classroom,interview_time,introduce,purpose,remark,init_time,update_time,interview_id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, registrationForm.getEmail());
            preparedStatement.setString(2, registrationForm.getPhone());
            preparedStatement.setInt(3, registrationForm.getIntentDepartment());
            preparedStatement.setInt(4, registrationForm.getGrade());
            preparedStatement.setString(5, registrationForm.getClassroom());
            SimpleDateFormat s1 = new SimpleDateFormat("hh:mm:ss");
            String date1 = s1.format(registrationForm.getInterviewTime());
            preparedStatement.setString(6, date1);
            preparedStatement.setString(7, registrationForm.getIntroduce());
            preparedStatement.setString(8, registrationForm.getPurpose());
            preparedStatement.setString(9, registrationForm.getRemark());
            DateTimeFormatter s2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            registrationForm.setInitTime(LocalDateTime.now());
            String date2 = s2.format(registrationForm.getInitTime());
            preparedStatement.setString(10, date2);
            SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            registrationForm.setUpdateTime(new Date());String date3 = s3.format(registrationForm.getUpdateTime());
            preparedStatement.setString(11, date3);
            preparedStatement.setInt(12,registrationForm.getIntervieweesId());
            int rows = preparedStatement.executeUpdate();
            System.out.println(registrationForm);
            System.out.println(rows);
            JDBCTools.free(connection);
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
    public Integer update(RegistrationForm registrationForm){
        try {Connection connection = JDBCTools.getConnection();
        String sql ="UPDATE registration_form SET interview_id = ?,email = ?,phone = ?,intent_department = ?,grade = ?,classroom = ?,interview_time = ?,introduce = ?,purpose = ?,remark = ?,init_time = ?,update_time = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,registrationForm.getIntervieweesId());
        preparedStatement.setString(2,registrationForm.getEmail());
        preparedStatement.setString(3,registrationForm.getPhone());
        preparedStatement.setInt(4,registrationForm.getId());
        preparedStatement.setInt(5,registrationForm.getId());
        preparedStatement.setString(6,registrationForm.getClassroom());
        SimpleDateFormat s1 = new SimpleDateFormat("hh:mm:ss");
        String date1 = s1.format(registrationForm.getInterviewTime());
        preparedStatement.setString(7, date1);
        preparedStatement.setString(8, registrationForm.getIntroduce());
        preparedStatement.setString(9, registrationForm.getPurpose());
        preparedStatement.setString(10, registrationForm.getRemark());
        DateTimeFormatter s2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        registrationForm.setInitTime(LocalDateTime.now());
        String date2 = s2.format(registrationForm.getInitTime());
        preparedStatement.setString(11, date2);
        SimpleDateFormat s3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        registrationForm.setUpdateTime(new Date());String date3 = s3.format(registrationForm.getUpdateTime());
        preparedStatement.setString(12, date3);
        preparedStatement.setInt(13,registrationForm.getId());
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows);
        JDBCTools.free(connection);
        return rows;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    public RegistrationForm selectByRegistrationId(Integer registrationId){
        try {Connection connection = JDBCTools.getConnection() ;
            String sql = "SELECT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            RegistrationForm registrationForm = new RegistrationForm();
            while (resultSet.next()){
                registrationForm.setId(resultSet.getInt("id"));
                registrationForm.setIntervieweesId(resultSet.getInt("interview_id"));
                registrationForm.setEmail(resultSet.getString("email"));
                registrationForm.setPhone(resultSet.getString("phone"));
                registrationForm.setClassroom(resultSet.getString("classroom"));
                registrationForm.setInterviewTime(resultSet.getDate("interview_time"));
                registrationForm.setIntroduce(resultSet.getString("introduce"));
                registrationForm.setPurpose(resultSet.getString("purpose"));
                registrationForm.setRemark(resultSet.getString("remark"));
                Instant instant = resultSet.getDate("intent_department").toInstant();
                registrationForm.setInitTime(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
                registrationForm.setIntentDepartment(resultSet.getInt("intent_department"));
                registrationForm.setGrade(resultSet.getInt("grade"));
                registrationForm.setUpdateTime(resultSet.getDate("update_time"));
            } JDBCTools.free(connection);
            return registrationForm;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }


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
