package store.data.product;

import javax.validation.constraints.*;

public class ProductData {

    @Digits(integer = 8, fraction = 0)
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 20, message = "Category should have between 2 and 20 characters and it should be an existing one")
    private String categoryName;
    @NotBlank
    @Size(min = 2, max = 20, message = "Name should have between 2 and 20 characters")
    private String name;
    @NotBlank
    @Size(min = 2, max = 200, message = "Description should have between 2 and 200 characters")
    private String description;
    @NotNull
    @Digits(integer = 8, fraction = 2, message = "Example: 4.99")
    private Double price;
    @NotNull
    @Digits(integer = 8, fraction = 0, message = "The stock should have 1->8 digits")
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
