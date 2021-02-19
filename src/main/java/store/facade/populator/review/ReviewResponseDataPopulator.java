package store.facade.populator.review;

import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.data.review.ReviewResponseData;
import store.facade.populator.Populator;

@Component
public class ReviewResponseDataPopulator implements Populator<ReviewData, ReviewResponseData> {

    @Override
    public void populate(ReviewResponseData reviewResponseData, ReviewData reviewData) {
        reviewResponseData.setId(reviewData.getId());
        reviewResponseData.setRating(reviewData.getRating());
        reviewResponseData.setText(reviewData.getText());
    }
}
