package tp_final_progra3.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class EstadoJuegoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;

    @Enumerated(EnumType.STRING)
    private Estado estado;
    private LocalDateTime fecha_actualizacion;
}
