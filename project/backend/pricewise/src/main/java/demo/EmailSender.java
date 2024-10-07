package demo;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


import demo.Encoder;

public class EmailSender {

    public static void email_sender(String target_email) {
        // 配置邮件服务器
        String host = "smtp.163.com";
        String username = "18858157930@163.com"; // 邮箱地址
        String password = "UGTcKUWgjgre6BfS"; // 邮箱密码

        // 配置邮件属性
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465"); // 通常使用587端口

        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            String encoded_email = Encoder.sha256(target_email);
            System.out.println(encoded_email);
            StringBuilder encoded_str = new StringBuilder();
            for (int i = 0; i < encoded_email.length(); i++) {
                if (encoded_email.charAt(i) <= '9')
                    encoded_str.append(encoded_email.charAt(i));
            }
            String verificationCode = encoded_str.substring(0, 6);
            System.out.println(verificationCode);
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(target_email));
            message.setSubject("price-wise：验证码");
            message.setText("【price-wise】您的验证码是: " + verificationCode);

            // 发送邮件
            Transport.send(message);
            System.out.println("验证码已发送至 " + target_email);
            // return verificationCode;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}