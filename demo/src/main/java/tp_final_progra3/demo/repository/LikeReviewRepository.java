package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.LikeReview;
import tp_final_progra3.demo.model.entity.Review;
import tp_final_progra3.demo.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeReviewRepository extends JpaRepository<LikeReview, Long> {
    Optional<LikeReview> findByUsuarioAndReview(Usuario usuario, Review review);
    Long contarByReview(Review review);
    List<LikeReview> findByReview(Review review);
}
