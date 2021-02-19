package store.data.order;

import java.util.List;

public class OrderLinesData {

    private List<OrderLineData> orderLines;
    private Double totalPrice;
    private Integer numberOfProducts;

    public List<OrderLineData> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineData> orderLines) {
        this.orderLines = orderLines;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
