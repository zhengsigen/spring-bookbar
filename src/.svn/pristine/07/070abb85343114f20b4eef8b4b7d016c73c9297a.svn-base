package com.c1801.spring.dzy.common.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * user：少
 * dateTime: 2019/8/24 10:15
 */
@Service
public class MailServiceImpl implements IMailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    //freemarker---FreeMarkerConfigurer
    @Autowired
    public Configuration configuration;

    //发送者
    @Value("${spring.mail.username}")
    public String USER_NAME;

    //文本分割
    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
    }


    @Override
    public void send(Email mail) {

        try {
            logger.info("发送普通文本邮件：{}", mail.getContent());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(USER_NAME);
            message.setTo(mail.getEmail());
            message.setSubject(mail.getSubject());
            message.setText(mail.getContent());
            mailSender.send(message);

        }catch (Exception e) {
            logger.info("邮件发送失败");
            e.printStackTrace();
        }
    }

    @Override
    public void sendFreemarker(Email mail) {

        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //这里可以自定义发信名称比如：工作流
            helper.setFrom(USER_NAME, "系统邮件通知");
            helper.setTo(mail.getEmail());
            helper.setSubject(mail.getSubject());
            Map<String, Object> model = new HashMap<>();
            model.put("mail", mail);
            model.put("exception", mail.getKvMap());
            Template template = configuration.getTemplate(mail.getTemplate());
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setText(text, true);
            mailSender.send(message);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
