package store.service.email.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import store.service.email.MailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendEmail(String[] to, String subject, String ftlFile, Map<String, Object> model) throws MessagingException, IOException, TemplateException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
        String htmlMessage = buildBodyMessage(model, ftlFile);

        helper.setSubject(subject);
        helper.setText(htmlMessage, true);
        helper.setTo(to);

        mailSender.send(mimeMessage);
    }

    private String buildBodyMessage(Map<String, Object> model, String ftlFile) throws IOException, TemplateException {

        Template registerEmailTemplate = configuration.getTemplate(ftlFile);
        return FreeMarkerTemplateUtils.processTemplateIntoString(registerEmailTemplate, model);
    }
}
