package store.service.email;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

public interface MailService {

    void sendEmail(String to, String subject, String text);

    void sendEmail(String[] to, String subject, String ftlFile, Map<String, Object> model) throws MessagingException, IOException, TemplateException;

}
