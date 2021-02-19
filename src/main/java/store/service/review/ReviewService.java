package store.service.review;

import store.model.Review;

import java.util.List;

public interface ReviewService {
    void addReview(Review review);
    List<Review> getAllReviews();
    void approve(Review review);
    Review getById(Long id);
}
