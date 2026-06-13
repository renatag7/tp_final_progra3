package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record JuegoResponseDTO(
        Long id,
        String titulo,
        String descripcion,
        LocalDate fecha_lanzamiento,
        Double rating_general,
        Set<String> generos,
        Set<String> plataformas,
        String developer
) {
}
