package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UsuarioFavorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_favoritos;

    @Column(nullable = false)
    private Integer posicion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;
}
