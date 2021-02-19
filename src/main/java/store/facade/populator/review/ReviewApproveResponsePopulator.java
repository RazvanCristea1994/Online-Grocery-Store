package store.facade.populator.review;

import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveResponseData;
import store.facade.populator.Populator;
import store.model.Review;

@Component
public class ReviewApproveResponsePopulator implements Populator<Review, ReviewApproveResponseData> {
    @Override
    public void populate(ReviewApproveResponseData reviewApproveResponseData, Review review) {
        reviewApproveResponseData.setText(review.getText());
        reviewApproveResponseData.setRating(review.getRating());
        reviewApproveResponseData.setId(review.getProduct().getId());
        reviewApproveResponseData.setEmail(review.getUser().getEmail());
        reviewApproveResponseData.setReviewId(review.getId());
    }
}
