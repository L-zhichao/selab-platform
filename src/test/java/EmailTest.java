import jakarta.mail.MessagingException;
import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.service.impl.QQEmailService;
import tyut.selab.loginservice.utils.SecurityUtil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static tyut.selab.loginservice.common.Constant.VERIFICATION_HTML_TEXT;

public class EmailTest {
//    @Test
//    public void test(){
//        EmailDaoImpl emailDao = new EmailDaoImpl();
//        Email email1 = null;
//        try {
//            email1 = emailDao.executeQueryOne(Email.class, "select * from sys_email where userId = 1");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(email1.getCreateTime());
//    }
//    @Test
//    public void deleteTest(){
//        EmailDaoImpl emailDao = new EmailDaoImpl();
//        int result = 0;
//        try {
//            result = emailDao.delete(1);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(result);
//    }
//    @Test
//    public void queryNumTest() {
//        EmailServiceImpl emailService = new EmailServiceImpl();
//        Integer i = null;
//        try {
//            i = emailService.queryNumForSameEmail("3388532526@qq.com");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(i);
//    }
//    @Test
//    public void sendEmail(){
//        QQEmailService emailService = new QQEmailService();
//        String head = "qq邮箱发送";
//        String body = "验证码信息";
//        try {
//            emailService.qqemail("3388532526@qq.com",head,body);
//        } catch (IOException | MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Test
//    public void falseEmailTest(){
//        QQEmailService emailService = new QQEmailService();
//        String head = "验证码";
//        String body = "这是你的验证码";
//        try {
//            System.out.println(QQEmailService.checkEmail("3388532526@qq.com"));
//            emailService.qqemail("3388532526@qq.com",head,body);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void test2(){
//        String sql = "select user_id userId,email,create_time createTime from sys_email where user_id = 1";
//        EmailDaoImpl emailDao = new EmailDaoImpl();
//        Email email = null;
//        try {
//            email = emailDao.executeQueryOne(Email.class,sql);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        } catch (InvocationTargetException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(email);
//    }
//    @Test
//    public void insertTest(){
//        EmailDaoImpl emailDao = new EmailDaoImpl();
//        Email email = new Email(4,"2072349810@qq.com");
//        try {
//            emailDao.insert(email);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Test
//    public void verityTest(){
//        System.out.println(SecurityUtil.getRandom());
//    }
//    @Test
//    public void getEmailNumTest(){
//        EmailServiceImpl emailService = new EmailServiceImpl();
//        try {
//            System.out.println(emailService.getEmailNum());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    @Test
//    public void emailTypeTest(){
//        String email = "@qq.com";
//        System.out.println(QQEmailService.checkEmail(email));
//    }
//    @Test
//    public void phoneTest(){
//        String phone = "1190851";
//        System.out.println(QQEmailService.checkPhone(null));
//    }
//    @Test
//    public void userNameTest(){
//        String userName = "atf@￥gushaobo";
//        System.out.println(QQEmailService.checkUserName(userName));
//    }
//    @Test
//    public void passwordTest(){
//        String password = "wW15博567897";
//        System.out.println(QQEmailService.checkPassword(password));
//    }
//    @Test
//    public void qqEmailTest2(){
//        String verify = SecurityUtil.getRandom();
//        String head = "平台注册验证码信息";
//        String type = "QQ邮箱注册平台";
//        String body = String.format(VERIFICATION_HTML_TEXT,head,"李少博",type,verify,"注册");
//        try {
//            QQEmailService.qqemail("3388532526@qq.com",head,body);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    @Test
//    public void formatTest(){
//        String head = "你好我是%s";
//        Object body = 15;
//        System.out.println(String.format(head,body));
//    }
}
