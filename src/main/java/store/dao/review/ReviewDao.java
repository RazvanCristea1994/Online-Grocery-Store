package store.dao.review;

import store.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDao {

    void save(Review review);

    void update(Review review);

    void delete(Long id);

    Optional<Review> getById(Long id);

    List<Review> getAll();

    void approve(Review review);
}
