package store.dao.review.impl;

import org.springframework.stereotype.Repository;
import store.dao.HibernateAbstractCrudRepository;
import store.dao.review.ReviewDao;
import store.model.Review;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDaoImpl extends HibernateAbstractCrudRepository<Long, Review> implements ReviewDao {

    @Override
    protected Class<Review> getValueClass() {
        return Review.class;
    }

    @Override
    public void approve(Review review) {
        update(review);
    }
}
