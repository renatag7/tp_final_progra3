package tp_final_progra3.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    private Long id_review;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String contenido;
    private float puntuacion;
    private LocalDateTime fechaPublicacion;
    private boolean contieneSpoilers;
}
