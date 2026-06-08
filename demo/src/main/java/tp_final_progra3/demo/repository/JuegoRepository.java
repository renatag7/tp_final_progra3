package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.Juego;

import java.util.Optional;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {


}
