package store.event.mailevent;

import store.model.Order;

import java.util.List;

public class DeleteOrderMailEvent extends MailEvent{

    public DeleteOrderMailEvent(Object source, List<Order> orders, String[] to) {
        super(source, orders, to);
    }

    @Override
    public String getModelKey() {
        return "orders";
    }

    @Override
    public String getTemplate() {
        return "deleted-orders-mail.ftl";
    }

    @Override
    public String getSubject() {
        return "The old orders has been deleted";
    }
}
