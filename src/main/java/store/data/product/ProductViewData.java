package store.data.product;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProductViewData {

    private Long categoryId;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    private Long id;
    private String description;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    private Double price;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer stock;

    public ProductViewData() {
    }

    public ProductViewData(Long categoryId, @NotEmpty @Size(min = 2, max = 20) String name, @NotNull Long id, String description, @NotNull @Digits(integer = 8, fraction = 2) Double price, @NotNull @Digits(integer = 8, fraction = 0) Integer stock) {
        this.categoryId = categoryId;
        this.name = name;
        this.id = id;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
