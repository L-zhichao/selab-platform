package tyut.selab.loginservice.service.impl;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;

import java.io.IOException;
import java.util.Properties;

public class QQEmailService {
    private static String text = "\"<div style=\\\"font-family: serif;line-height: 22px;padding: 30px\\\">\\n\" +\n" +
            "                \"    <div style=\\\"display: flex;flex-direction: column;align-items: center\\\">\\n\" +\n" +
            "                \"        <div style=\\\"margin: 10px auto 20px;text-align: center\\\">\\n\" +\n" +
            "                \"            <div style=\\\"line-height: 32px;font-size: 26px;font-weight: bold;color: #000000\\\">\\n\" +\n" +
            "                \"                嘿！你在 %s 中收到一条新消息。\\n\" +\n" +
            "                \"            </div>\\n\" +\n" +
            "                \"            <div style=\\\"font-size: 16px;font-weight: bold;color: rgba(0, 0, 0, 0.19);margin-top: 21px\\\">\\n\" +\n" +
            "                \"                %s\\n\" +\n" +
            "                \"            </div>\\n\" +\n" +
            "                \"        </div>\\n\" +\n" +
            "                \"        <div style=\\\"min-width: 250px;max-width: 800px;min-height: 128px;background: #F7F7F7;border-radius: 10px;padding: 32px\\\">\\n\" +\n" +
            "                \"            <div>\\n\" +\n" +
            "                \"                <div style=\\\"font-size: 18px;font-weight: bold;color: #C5343E\\\">\\n\" +\n" +
            "                \"                    %s\\n\" +\n" +
            "                \"                </div>\\n\" +\n" +
            "                \"                <div style=\\\"margin-top: 6px;font-size: 16px;color: #000000\\\">\\n\" +\n" +
            "                \"                    <p>\\n\" +\n" +
            "                \"                        %s\\n\" +\n" +
            "                \"                    </p>\\n\" +\n" +
            "                \"                </div>\\n\" +\n" +
            "                \"            </div>\\n\" +\n" +
            "                \"            %s\\n\" +\n" +
            "                \"            <a style=\\\"width: 150px;height: 38px;background: #ef859d38;border-radius: 32px;display: flex;align-items: center;justify-content: center;text-decoration: none;margin: 40px auto 0\\\"\\n\" +\n" +
            "                \"               href=\\\"https://poetize.cn\\\" target=\\\"_blank\\\">\\n\" +\n" +
            "                \"                <span style=\\\"color: #DB214B\\\">有朋自远方来</span>\\n\" +\n" +
            "                \"            </a>\\n\" +\n" +
            "                \"        </div>\\n\" +\n" +
            "                \"        <div style=\\\"margin-top: 20px;font-size: 12px;color: #00000045\\\">\\n\" +\n" +
            "                \"            此邮件由 %s 自动发出，直接回复无效（一天最多发送 \" + CommonConst.COMMENT_IM_MAIL_COUNT + \" 条通知邮件和 \" + CommonConst.CODE_MAIL_COUNT + \" 条验证码邮件），退订请联系管理员。\\n\" +\n" +
            "                \"        </div>\\n\" +\n" +
            "                \"    </div>\\n\" +\n" +
            "                \"</div>\";";
    public static void qqemail(String QQmail, String head, String body) throws AddressException, MessagingException, IOException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
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
//        message.setText(body);
        // 得到邮差对象
        Transport transport = session.getTransport();
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        MimeMultipart mp =  new MimeMultipart();
        mimeBodyPart.setContent(body,"text/html;charset=UTF-8");
        mp.addBodyPart(mimeBodyPart);
        message.setContent(mp);

        // 连接自己的邮箱账户
        transport.connect("2072349810@qq.com", "ioejciptntgtdabe");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        int i = 0;
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 判断用户注册时输入的邮箱信息是否正确
     * @param QQemail
     * @return 默认返回false，如果格式正确返回true
     */
    public static boolean checkEmail(String QQemail){
        String email = QQemail;
        boolean flag = false;
        if(email.matches("[1-9]\\d{7,10}@qq\\.com")){
            flag = true;
        }
        return flag;
    }
    public static boolean checkPhone(String phone){
        //判断设手机号码格式是否正确
        String pho = phone;
        boolean flag = false;
        if (pho.matches("1[3-9]\\d{9}")){
            flag = true;
        }
        return flag;
    }
    public static boolean checkUserName(String userName){
        String name = userName;
        boolean flag = false;
        //用户名6到12个字符，可以包含中文、大小写字母、和数字
        if(name.matches("^[\u4e00-\u9fa5a-zA-Z0-9]{6,12}$")){
            flag = true;
        }
        return flag;
    }
    public static boolean checkPassword(String password){
        String word = password;
        boolean flag = false;
        //至少6个字符，最多12个字符，其中至少1个大写字母，1个小写字母和1个数字,不能包含特殊字符，不可以是中文
        if(word.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$")){
            flag = true;
        }
        return flag;
    }
}