package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_review;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_juego")
    private Juego juego;

    @Column(nullable = false)
    private String contenido;

    private Float puntuacion;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    private boolean contieneSpoilers;

    @Column(nullable = false)
    private Integer cantidadLikes = 0;
}
