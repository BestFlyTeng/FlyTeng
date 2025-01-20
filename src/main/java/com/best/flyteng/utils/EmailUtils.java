package com.best.flyteng.utils;

import com.best.flyteng.config.BestException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Slf4j
@Component
public class EmailUtils {
  @Value("${spring.mail.host}")
  private String host;
  @Value("${spring.mail.port}")
  private int port;
  @Value("${spring.mail.username}")
  private String username;
  @Value("${spring.mail.password}")
  private String password;

  public Boolean sendEmail(String to, String subject, String body) throws BestException {
    Properties props = new Properties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", port);
    props.put("mail.smtp.auth", true);
    Session session = Session.getInstance(props, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });
    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username)); // 发件人地址
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); // 收件人地址
      message.setSubject(subject); // 邮件主题
      message.setText(body); // 邮件正文

      Transport.send(message);
      log.info("向 {} 发送邮件成功", to);
    } catch (Exception e) {
      throw new BestException("发送邮件异常");
    }
    return true;
  }
}
