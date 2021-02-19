package store.facade.review;

import store.data.review.ReviewApproveData;
import store.data.review.ReviewApproveResponseData;
import store.data.review.ReviewData;
import store.data.review.ReviewResponseData;

import java.util.List;

public interface ReviewFacade {

    ReviewResponseData save(ReviewData reviewData, String email);
    List<ReviewApproveResponseData> getAllReview();
    void approve(ReviewApproveData reviewApproveData);
}
