package store.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailProperties {

    @Value("${store.mail.host}")
    private String host;

    @Value(value = "${store.mail.port}")
    private Integer port;

    @Value("${store.mail.username}")
    private String username;

    @Value("${store.mail.password}")
    private String password;

    @Value("${store.mail.transport.protocol}")
    private String transportProtocol;

    @Value("${store.mail.smtp.auth}")
    private Boolean smtpAuthentication;

    @Value("${store.mail.smtp.starttls.enable}")
    private Boolean startTtls;

    @Value("${store.mail.debug}")
    private Boolean debug;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public Boolean getSmtpAuthentication() {
        return smtpAuthentication;
    }

    public Boolean getStartTtls() {
        return startTtls;
    }

    public Boolean getDebug() {
        return debug;
    }
}
