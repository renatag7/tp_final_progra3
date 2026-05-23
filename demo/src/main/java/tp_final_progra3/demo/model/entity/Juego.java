package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "juegos")
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
    private Set<Genero> generos = new HashSet<>();

    @OneToMany(mappedBy = "juego")
    private List<EstadoJuegoUsuario> estados_juego = new ArrayList<>();

    @OneToMany(mappedBy = "juego")
    private List<Review> reviewsJuego = new ArrayList<>();

    @OneToMany(mappedBy = "juego")
    private List<UsuarioFavorito> favoritos_usuarios = new ArrayList<>();


    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private LocalDate fecha_lanzamiento;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private Double rating_general;
}
