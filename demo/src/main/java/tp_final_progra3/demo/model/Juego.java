package tp_final_progra3.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_juego;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "juego_plataforma",
            joinColumns = @JoinColumn(name = "id_juego"),
            inverseJoinColumns = @JoinColumn(name = "id_plataforma")
    )
    private Set<Plataforma> plataformas = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "juego_genero",
            joinColumns = @JoinColumn(name = "id_juego"),
            inverseJoinColumns = @JoinColumn(name = "id_genero")
    )
    private Set<Genero> generos;

    private String titulo;
    private String sinopsis;
    private Date fecha_lanzamiento;
    private String desarrolladora;
    private String duracion_estimada;
    private float rating_general;
}
