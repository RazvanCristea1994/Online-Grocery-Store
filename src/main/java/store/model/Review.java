package store.model;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    private Long id;
    private User user;
    private Product product;
    private Integer rating;
    private String text;
    private boolean approved = false;

    public Review(Long id, User user, Product product, Integer rating, String text){
        this.id = id;
        this.user = user;
        this.product = product;
        this.rating = rating;
        this.text = text;
        this.approved = false;
    }

    public Review(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "email", referencedColumnName = "email")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(targetEntity = Product.class, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "rating", nullable = false)
    public Integer getRating() { return rating; }

    public void setRating(Integer rating) { this.rating = rating; }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "approved")
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
