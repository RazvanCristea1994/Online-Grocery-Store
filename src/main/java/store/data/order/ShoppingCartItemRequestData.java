package store.data.order;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ShoppingCartItemRequestData {


    @NotNull
    private Long id;

    @NotNull
    @Min(value = 1, message = "The quantity must be at least 1!")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
