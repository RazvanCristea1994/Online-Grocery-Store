package store.data.product;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductData {

    @Digits(integer = 8, fraction = 0)
    private Long id;
    @NotNull
    @Size(min = 2, max = 20)
    private String categoryName;
    @Size(min = 2, max = 20)
    private String name;
    private String description;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    private Double price;
    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer stock;

    public ProductData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
