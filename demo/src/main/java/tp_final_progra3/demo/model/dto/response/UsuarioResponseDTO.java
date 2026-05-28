package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String nombre,
        String biografia,
        String pais,
        Boolean perfil_publico,
        LocalDate fecha_registro,
        Integer cantSeguidos,
        Integer cantSeguidores
) {
}
