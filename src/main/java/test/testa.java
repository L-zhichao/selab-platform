package test;

import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.utils.JDBCTools;
import tyut.selab.recruitservice.domain.RegistrationForm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class testa {
    public static void main(String[] args) throws SQLException {
        RegistrationDaoImpl a = new RegistrationDaoImpl();
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setId(99);
        registrationForm.setIntervieweesId(8888888);
        registrationForm.setEmail("1");
        registrationForm.setPhone("1");
        registrationForm.setClassroom("1");
        registrationForm.setInterviewTime(new Date());
        registrationForm.setIntroduce("1");
        registrationForm.setPurpose("1");
        registrationForm.setRemark("1");
        registrationForm.setIntentDepartment(0);
        registrationForm.setGrade(0);
        a.update(registrationForm);
        Connection connection = JDBCTools.getConnection();
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
        System.out.println("---------------");
        JDBCTools.free(connection);
    }
}
