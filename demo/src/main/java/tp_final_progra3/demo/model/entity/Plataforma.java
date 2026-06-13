package tp_final_progra3.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//link a pagina para comprar
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "plataformas")
    private Set<Juego> juegos = new HashSet<>();


}
