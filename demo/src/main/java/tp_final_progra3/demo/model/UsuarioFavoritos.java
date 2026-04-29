package tp_final_progra3.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class UsuarioFavoritos {
    @Id
    private Long id_favoritos;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Min(1)
    @Max(4)
    private Integer posicion;
}
