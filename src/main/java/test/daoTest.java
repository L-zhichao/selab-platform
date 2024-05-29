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
        Integer userId = 12356;
        RegistrationDao registrationDao = new RegistrationDaoImpl();
        System.out.println(registrationDao.selectByUserId(userId));
    }
}