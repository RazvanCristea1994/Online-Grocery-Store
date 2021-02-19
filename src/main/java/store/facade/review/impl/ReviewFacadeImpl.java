package store.facade.review.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.data.review.ReviewApproveData;
import store.data.review.ReviewApproveResponseData;
import store.data.review.ReviewData;
import store.data.review.ReviewResponseData;
import store.facade.converter.Converter;
import store.facade.review.ReviewFacade;
import store.model.Review;
import store.service.review.ReviewService;
import store.service.user.UserService;

import java.util.List;

@Component
public class ReviewFacadeImpl implements ReviewFacade {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private Converter<ReviewData, Review> reverseConverter;

    @Autowired
    private Converter<ReviewData, ReviewResponseData> converter;

    @Autowired
    private UserService userService;

    @Autowired
    private  Converter<Review, ReviewApproveResponseData> reviewApproveResponseConverter;

    @Autowired
    private Converter<ReviewApproveData, Review> reviewApproveReverseConverter;


    @Override
    public ReviewResponseData save(ReviewData reviewData, String email) {
        Review review = reverseConverter.convert(reviewData);
        review.setUser(userService.getByEmail(email).orElse(null));
        reviewService.addReview(review);
        ReviewResponseData reviewResponseData = converter.convert(reviewData);
        reviewResponseData.setEmail(email);
        return reviewResponseData;
    }

    @Override
    public List<ReviewApproveResponseData> getAllReview() {
        List<Review> reviewDataList = reviewService.getAllReviews();
        return reviewApproveResponseConverter.convertAll(reviewDataList);
    }

    @Override
    public void approve(ReviewApproveData reviewApproveData) {
        Review review = reviewApproveReverseConverter.convert(reviewApproveData);
        reviewService.approve(review);
    }
}
