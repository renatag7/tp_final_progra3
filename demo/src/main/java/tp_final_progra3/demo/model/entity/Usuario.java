package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tp_final_progra3.demo.model.enums.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String biografia;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    private String pais;

    @Column(nullable = false)
    private boolean perfilPublico;

    @Column(nullable = false)
    private boolean activo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<ListaPersonalizada> listas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<EstadoJuegoUsuario> estados_usuario = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioFavorito> juegos_favoritos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "seguimiento",
            joinColumns = @JoinColumn(name = "id_seguidor"),
            inverseJoinColumns = @JoinColumn(name = "id_seguido")
    )
    private Set<Usuario> seguidos = new HashSet<>();

    @ManyToMany(mappedBy = "seguidos")
    private Set<Usuario> seguidores = new HashSet<>();
}
