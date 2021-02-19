package store.service.review.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dao.review.ReviewDao;
import store.model.Review;
import store.service.review.ReviewService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public void addReview(Review review) {
        reviewDao.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.getAll();
    }

    @Override
    public void approve(Review review) {
        reviewDao.approve(review);
    }

    @Override
    public Review getById(Long id) {
        return reviewDao.getById(id).get();
    }
}
