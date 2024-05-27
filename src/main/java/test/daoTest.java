package test;
import tyut.selab.recruitservice.dao.*;
import tyut.selab.recruitservice.dao.impl.RegistrationDaoImpl;
import tyut.selab.recruitservice.domain.RegistrationForm;
import tyut.selab.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

public class daoTest {
    public static void main(String[] args) throws Exception {

        RegistrationDaoImpl a = new RegistrationDaoImpl();
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setId(99);
        registrationForm.setIntervieweesId(250);
        registrationForm.setEmail("1");
        registrationForm.setPhone("1");
        registrationForm.setClassroom("1");
        registrationForm.setInterviewTime(new Date());
        registrationForm.setIntroduce("1");
        registrationForm.setPurpose("2");
        registrationForm.setRemark("1");
        registrationForm.setIntentDepartment(0);
        registrationForm.setGrade(0);
       a.update(registrationForm);
    }
}