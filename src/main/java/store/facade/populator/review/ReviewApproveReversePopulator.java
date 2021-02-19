package store.facade.populator.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveData;
import store.facade.populator.Populator;
import store.model.Review;
import store.service.review.ReviewService;

@Component
public class ReviewApproveReversePopulator implements Populator<ReviewApproveData, Review> {

    @Autowired
    private ReviewService reviewService;

    @Override
    public void populate(Review review, ReviewApproveData reviewApproveData) {
        Review review1 = reviewService.getById(reviewApproveData.getId());
        review.setApproved(reviewApproveData.isApproved());
        review.setId(reviewApproveData.getId());
        review.setProduct(review1.getProduct());
        review.setRating(review1.getRating());
        review.setText(review1.getText());
        review.setUser(review1.getUser());
    }
}
