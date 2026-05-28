package tp_final_progra3.demo.model.dto.response;

import tp_final_progra3.demo.model.enums.Estado;

import java.time.LocalDate;

public record EstadoJuegoResponseDTO(
        Long id,
        Long juego,
        Estado estado,
        LocalDate fecha_actualizacion
) {
}
