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

}
