package tp_final_progra3.demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_plataforma;

    @ManyToMany(mappedBy = "plataformas")
    private Set<Juego> juegos = new HashSet<>();
}
