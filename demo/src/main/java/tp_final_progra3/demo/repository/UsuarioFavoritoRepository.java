package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.entity.UsuarioFavorito;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioFavoritoRepository extends JpaRepository<UsuarioFavorito, Long> {
    List<UsuarioFavorito> findByUsuarioOrderByPosicionDesc(Usuario usuario);
    Optional<UsuarioFavorito> findByUsuarioAndJuego(Usuario usuario, Juego juego);
    Optional<UsuarioFavorito> findByUsuarioAndPosicion(Usuario usuario, Integer posicion);
    Long countByUsuario(Usuario usuario);
}
