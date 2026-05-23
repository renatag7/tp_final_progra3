package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//link a pagina para comprar
@Entity
@Getter
@Setter
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plataforma;

    @ManyToMany(mappedBy = "plataformas")
    private Set<Juego> juegos = new HashSet<>();

    private String fabricante;
}
