package store.data.product;

import store.model.Product;

import javax.validation.constraints.*;
import java.util.List;

public class CategoryData {

    private Long id;
    @NotEmpty
    @Size(min = 2, max = 20, message = "The name should be between 2 and 20 characters")
    private String name;
    private List<Product> productList;

    public CategoryData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
