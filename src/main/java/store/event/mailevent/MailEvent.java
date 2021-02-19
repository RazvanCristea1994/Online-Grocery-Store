package store.event.mailevent;

import org.springframework.context.ApplicationEvent;

public abstract class MailEvent extends ApplicationEvent {

    private Object modelValue;

    private String[] to;

    public MailEvent(Object source, Object modelValue, String[] to) {
        super(source);
        this.modelValue = modelValue;
        this.to = to;
    }

    public Object getModelValue() {
        return modelValue;
    }

    public String[] getTo(){
        return to;
    }

    public abstract String getModelKey();

    public abstract String getTemplate();

    public abstract String getSubject();
}
