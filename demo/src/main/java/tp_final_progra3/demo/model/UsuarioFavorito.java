package tp_final_progra3.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class UsuarioFavorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_favoritos;

    @Min(1)
    @Max(4)
    private Integer posicion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;
}
