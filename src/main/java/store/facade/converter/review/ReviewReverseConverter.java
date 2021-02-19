package store.facade.converter.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;
import store.model.Review;

@Component
public class ReviewReverseConverter implements Converter<ReviewData, Review> {

    @Autowired
    private Populator<ReviewData, Review> reviewReversePopulator;

    @Override
    public Review convert(ReviewData reviewData) {
        Review review = new Review();
        reviewReversePopulator.populate(review, reviewData);
        return review;
    }
}
