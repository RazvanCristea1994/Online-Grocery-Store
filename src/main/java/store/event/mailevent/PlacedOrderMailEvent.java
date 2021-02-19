package store.event.mailevent;

public class PlacedOrderMailEvent extends MailEvent{

    public PlacedOrderMailEvent(Object source, Object modelValue, String[] to) {
        super(source, modelValue, to);
    }

    @Override
    public String getModelKey() {
        return "placedOrder";
    }

    @Override
    public String getTemplate() {
        return "placed-order-mail.ftl";
    }

    @Override
    public String getSubject() {
        return "Online Grocery Store. Placed order confirmation.";
    }
}
