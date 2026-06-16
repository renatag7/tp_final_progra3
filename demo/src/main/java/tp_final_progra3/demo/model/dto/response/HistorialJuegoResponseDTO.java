package tp_final_progra3.demo.model.dto.response;

import tp_final_progra3.demo.model.enums.Estado;

import java.time.LocalDate;

public record HistorialJuegoResponseDTO(
        String tituloJuego,
        Estado estado,
        LocalDate fecha_actualizacion
) {
}
