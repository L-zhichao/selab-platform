import jakarta.mail.MessagingException;
import org.junit.Test;
import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;
import tyut.selab.loginservice.service.impl.QQEmailService;
import tyut.selab.loginservice.utils.SecurityUtil;

import java.io.IOException;

public class EmailTest {
    @Test
    public void test(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        Email email1 = emailDao.executeQueryOne(Email.class, "select * from sys_email where userId = 1");
        System.out.println(email1.getCreateTime());
    }
    @Test
    public void deleteTest(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        int result = emailDao.delete(1);
        System.out.println(result);
    }
    @Test
    public void queryNumTest(){
        EmailServiceImpl emailService = new EmailServiceImpl();
        Integer i = emailService.queryNumForSameEmail("3388532526@qq.com");
        System.out.println(i);
    }
    @Test
    public void sendEmail(){
        QQEmailService emailService = new QQEmailService();
        String head = "qq邮箱发送";
        String body = "验证码信息";
        try {
            emailService.qqemail("3388532526@qq.com",head,body);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void falseEmailTest(){
        QQEmailService emailService = new QQEmailService();
        String head = "verification information";
        String body = "Dear User, Welcome to register our system!!!<br>" +
                "Here is your verification code:156478.<br>" +
                "The validity period is 30 seconds, please complete the verification code within the specified period of time<br>";
        try {
            System.out.println(QQEmailService.checkEmail("3388532526@qq.com"));
            emailService.qqemail("654645@qq.com",head,body);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        String sql = "select user_id userId from sys_email where user_id = 1";
        EmailDaoImpl emailDao = new EmailDaoImpl();
        Email email = emailDao.executeQueryOne(Email.class,sql);
        System.out.println(email);
    }
    @Test
    public void insertTest(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        Email email = new Email(4,"2072349810@qq.com");
        emailDao.insert(email);
    }
    @Test
    public void verityTest(){
        System.out.println(SecurityUtil.getRandom());
    }
    @Test
    public void getEmailNumTest(){
        EmailServiceImpl emailService = new EmailServiceImpl();
        System.out.println(emailService.getEmailNum());

    }
}
