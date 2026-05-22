package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;

public record EstadoJuegoResponseDTO(
        Long id,
        Long juego,
        String estado,
        LocalDate fecha_actualizacion
) {
}
