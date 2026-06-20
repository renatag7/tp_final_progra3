package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import tp_final_progra3.demo.security.RolEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "usuarios")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

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

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

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

    @ManyToMany
    @JoinTable(
            name = "usuarios_bloqueados",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_bloqueado")
    )
    private Set<Usuario> usuariosBloqueados = new HashSet<>();


    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<RolEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Notificacion> notificaciones = new ArrayList<>();
}
