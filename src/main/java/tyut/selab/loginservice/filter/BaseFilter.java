package tyut.selab.loginservice.filter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.Filter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 创建一个父类，用来封装验证登录等操作的方法
 */
public abstract class BaseFilter implements Filter {
    /**
     *发送邮件的逻辑
     */
    public void qqemail(String QQmail,String head,String body) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        FileInputStream ips = new FileInputStream("/qqemail.properties");
        properties.load(ips);
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("2072349810@qq.com"));
        // 设置收件人邮箱地址
        message.setRecipients(Message.RecipientType.TO,
                new InternetAddress[]{new InternetAddress(QQmail)});
        //new InternetAddress();设置同时发送多个好友
        // 设置邮件标题
        message.setSubject(head);
        // 设置邮件内容
        message.setText(body);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("2072349810@qq.com", "sriknqmsvzqsebbc");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        int i = 0;
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("成功！");
        transport.close();
    }
}
