package store.facade.converter.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Review;

@Component
public class ReviewConverter implements Converter<Review, ReviewData> {

    @Autowired
    private Populator<Review, ReviewData> reviewPopulator;

    @Override
    public ReviewData convert(Review review) {
        ReviewData reviewData = new ReviewData();
        reviewPopulator.populate(reviewData, review);
        return reviewData;
    }
}
