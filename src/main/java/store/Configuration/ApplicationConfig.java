package store.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;

@Configuration
public class ApplicationConfig {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setPort(mailProperties.getPort());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProperties.getTransportProtocol());
        props.put("mail.smtp.auth", mailProperties.getSmtpAuthentication());
        props.put("mail.smtp.starttls.enable", mailProperties.getStartTtls());
        props.put("mail.debug", mailProperties.getDebug());

        return mailSender;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {

        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean() {

        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/WEB-INF/pages/ftl/");
        return freeMarkerConfigurationFactoryBean;
    }
}
