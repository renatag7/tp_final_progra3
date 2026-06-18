package tp_final_progra3.demo.model.dto.response;

import tp_final_progra3.demo.model.enums.Estado;

import java.time.LocalDate;

public record EstadoJuegoResponseDTO(
        String tituloJuego,
        Estado estado,
        LocalDate fechaActualizacion
) {
}
