package store.facade.populator.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.facade.populator.Populator;
import store.model.Review;
import store.service.product.ProductService;
import store.service.user.UserService;

@Component
public class ReviewReversePopulator implements Populator<ReviewData, Review> {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public void populate(Review review, ReviewData reviewData) {

        review.setProduct(productService.getById(reviewData.getId()));
        review.setRating(reviewData.getRating());
        review.setText(reviewData.getText());
    }
}
