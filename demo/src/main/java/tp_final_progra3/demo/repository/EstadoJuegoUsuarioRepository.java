package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Estado;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoJuegoUsuarioRepository extends JpaRepository<EstadoJuegoUsuario, Long > {
    List<EstadoJuegoUsuario> findByUserOrderByFecha(Usuario usuario);
    List<EstadoJuegoUsuario> findByUserAndEstadoOrderByFecha(Usuario usuario, Estado estado);
    Optional<EstadoJuegoUsuario> findByUserAndJuego(Usuario usuario, Juego juego);
}
