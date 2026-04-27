package tp_final_progra3.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UsuarioFavoritos {
    @Id
    private Long id_favoritos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
