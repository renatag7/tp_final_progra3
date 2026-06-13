package tp_final_progra3.demo.security;

import jakarta.persistence.*;
import lombok.*;
import tp_final_progra3.demo.model.enums.Rol;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name", nullable = false, unique = true)
    private Rol rol;


}
