package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;
import java.util.List;

public record ListaPersonalizadaResponseDTO(
        Long id_lista,
        String nombre,
        String descripcion,
        LocalDate fecha_creacion,
        List<JuegoResponseDTO> juegos
) {
}
