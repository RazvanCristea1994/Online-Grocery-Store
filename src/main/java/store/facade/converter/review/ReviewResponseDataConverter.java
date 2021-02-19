package store.facade.converter.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewData;
import store.data.review.ReviewResponseData;
import store.facade.converter.Converter;
import store.facade.populator.Populator;

@Component
public class ReviewResponseDataConverter implements Converter<ReviewData, ReviewResponseData> {

    @Autowired
    private Populator<ReviewData, ReviewResponseData> reviewReversePopulator;

    @Override
    public ReviewResponseData convert(ReviewData reviewData) {
        ReviewResponseData reviewResponseData = new ReviewResponseData();
        reviewReversePopulator.populate(reviewResponseData, reviewData);
        return reviewResponseData;
    }
}
