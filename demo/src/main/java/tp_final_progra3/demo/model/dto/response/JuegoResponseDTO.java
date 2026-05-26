package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;
import java.util.List;

public record JuegoResponseDTO(
        Long id,
        String titulo,
        String descripcion,
        LocalDate fecha_lanzamiento,
        Double rating_general,
        List<String> generos,
        List<String> plataformas,
        String developer
) {
}
