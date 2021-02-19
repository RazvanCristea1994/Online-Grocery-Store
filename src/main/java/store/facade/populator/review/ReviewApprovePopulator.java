package store.facade.populator.review;

import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveData;
import store.facade.populator.Populator;
import store.model.Review;

@Component
public class ReviewApprovePopulator implements Populator<Review, ReviewApproveData> {

    @Override
    public void populate(ReviewApproveData reviewApproveData, Review review) {
            reviewApproveData.setId(review.getId());
            reviewApproveData.setApproved(review.isApproved());
    }
}
