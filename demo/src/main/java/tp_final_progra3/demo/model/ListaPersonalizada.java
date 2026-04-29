package tp_final_progra3.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class ListaPersonalizada {
    @Id
    private Long id_lista;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "lista_juegos",
            joinColumns = @JoinColumn(name = "id_lista"),
            inverseJoinColumns = @JoinColumn(name = "id_juego")
    )
    private Set<Juego> juegos;

    private String nombre;
    private String descripcion;
    private LocalDateTime fecha_creacion;
    private boolean esPublica;
}
