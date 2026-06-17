package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.ComentarioReview;
import tp_final_progra3.demo.model.entity.Review;

import java.util.List;

@Repository
public interface ComentarioReviewRepository extends JpaRepository<ComentarioReview, Long> {
    List<ComentarioReview> findByReview(Review review);
}
