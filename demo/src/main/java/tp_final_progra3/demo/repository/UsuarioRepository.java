package tp_final_progra3.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Rol;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    @Query("""
            SELECT u
            FROM Usuario u
            JOIN u.roles r
            WHERE r.rol = :rol
            """)
    List<Usuario> findByRol(@Param("rol") Rol rol);

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByUsername(String username);

}
