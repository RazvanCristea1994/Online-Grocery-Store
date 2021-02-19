package store.facade.converter.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveResponseData;
import store.facade.converter.Converter;
import store.facade.populator.review.ReviewApproveResponsePopulator;
import store.model.Review;

@Component
public class ReviewApproveResponseConvertor implements Converter<Review, ReviewApproveResponseData> {
   @Autowired
   private ReviewApproveResponsePopulator reviewApproveResponsePopulator;

    @Override
    public ReviewApproveResponseData convert(Review review) {
        ReviewApproveResponseData reviewApproveResponseData = new ReviewApproveResponseData();
        reviewApproveResponsePopulator.populate(reviewApproveResponseData,review);
        return reviewApproveResponseData;
    }
}
