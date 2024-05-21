package tyut.selab.loginservice.service.impl;

import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.dto.UserLoginReq;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.EmailService;
import tyut.selab.loginservice.service.LoginService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * @className: EmailServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:38
 * @version: 1.0
 */
public class EmailServiceImpl implements EmailService {
    EmailDaoImpl emailDao = new EmailDaoImpl();

    @Override
    public Integer save(Email email) {
        int result = emailDao.insert(email);
        return result;
    }
    @Override
    public Integer update(Email email) {
        Integer i = emailDao.update(email);
        return i;
    }
    @Override
    public Integer queryNumForSameEmail(String email) {
        Integer countOfEmail = emailDao.selectNumForSameEmail(email);
        return countOfEmail;
    }
    // 生成随机验证码
    public String generateRandomCode() {
        Random random = new Random();
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }

    // 发送验证码邮件
    public void sendVerificationCodeEmail(String userEmail, String code) {
        // 使用JavaMail API发送邮件
        // 配置SMTP服务器信息
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        // 创建Session对象
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your_email@example.com", "your_password");
            }
        });

        try {
            // 创建Message对象
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@example.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("验证码登录");
            message.setText("您的验证码是：" + code);

            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public Map<String, String> verificationCodes = new HashMap<>();

    // 验证用户输入的验证码是否正确
    public boolean validateVerificationCode(String userEmail, String code) {
        String storedCode = verificationCodes.get(userEmail);
        return storedCode != null && storedCode.equals(code);
    }
}

