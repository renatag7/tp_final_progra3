package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;
import tp_final_progra3.demo.model.entity.Usuario;

import java.util.List;

@Repository
public interface EstadoJuegoUsuarioRepository extends JpaRepository<EstadoJuegoUsuario, Long > {
    List<EstadoJuegoUsuario> findByUserOrderByFecha(Usuario usuario);
}
