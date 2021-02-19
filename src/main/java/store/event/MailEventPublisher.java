package store.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import store.event.mailevent.MailEvent;

@Component
public class MailEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher (ApplicationEventPublisher publisher) {

        this.publisher = publisher;
    }

    public void publish(MailEvent event) {

        publisher.publishEvent(event);
    }
}
