package store.event;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import store.event.mailevent.MailEvent;
import store.service.email.MailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailEventHandler implements ApplicationListener<MailEvent>  {

    @Autowired
    private MailService mailService;

    @Override
    public void onApplicationEvent(MailEvent event) {

        try {
            Map<String, Object> model = new HashMap<>();
            model.put(event.getModelKey(), event.getModelValue());
            String subject = event.getSubject();
            String templateFile = event.getTemplate();
            String[] to = event.getTo();
            mailService.sendEmail(to, subject, templateFile, model);
        } catch (MessagingException | TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
