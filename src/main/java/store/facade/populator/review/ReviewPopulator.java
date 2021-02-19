package store.facade.populator.review;

import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.facade.populator.Populator;
import store.model.Review;

@Component
public class ReviewPopulator implements Populator<Review, ReviewData> {

    @Override
    public void populate(ReviewData reviewData, Review review) {
        reviewData.setId(review.getProduct().getId());
        reviewData.setRating(review.getRating());
        reviewData.setText(review.getText());
    }
}
