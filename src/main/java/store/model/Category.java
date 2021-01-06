package store.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    private Long id;
    private String name;
    private List<Product> productList;

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
