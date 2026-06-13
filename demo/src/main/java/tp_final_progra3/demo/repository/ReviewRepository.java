package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tp_final_progra3.demo.model.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    List<Review> findByJuegoId(Long juegoId);
    List<Review> findByUsuarioId(Long userId);
}
