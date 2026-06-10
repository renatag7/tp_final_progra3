package tp_final_progra3.demo.exceptions.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp_final_progra3.demo.model.enums.Rol;

import java.util.Optional;
@Repository
public interface RolRepository extends CrudRepository<RolEntity, Long> {
    Optional<RolEntity> findByRoleEnum(Rol roleEnum);
}
