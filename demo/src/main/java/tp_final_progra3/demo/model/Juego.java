package tp_final_progra3.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

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

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<EstadoJuegoUsuario> estados_juego = new ArrayList<>();

    @OneToMany(mappedBy = "juego", cascade = CascadeType.ALL)
    private List<Review> reviewsJuego = new ArrayList<>();

    private String titulo;
    private String sinopsis;
    private Date fecha_lanzamiento;
    private String desarrolladora;
    private String duracion_estimada;
    private float rating_general;
}
