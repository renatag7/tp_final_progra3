package tp_final_progra3.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    private Long id_review;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
