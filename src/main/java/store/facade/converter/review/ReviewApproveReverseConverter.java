package store.facade.converter.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveData;
import store.facade.converter.Converter;
import store.facade.populator.review.ReviewApproveReversePopulator;
import store.model.Review;

@Component
public class ReviewApproveReverseConverter implements Converter<ReviewApproveData, Review> {

    @Autowired
    private ReviewApproveReversePopulator reviewApproveReversePopulator;

    @Override
    public Review convert(ReviewApproveData reviewApproveData) {
        Review review = new Review();
        reviewApproveReversePopulator.populate(review,reviewApproveData);
        return review;
    }
}
