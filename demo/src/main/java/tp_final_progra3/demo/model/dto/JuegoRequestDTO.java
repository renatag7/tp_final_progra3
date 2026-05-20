package tp_final_progra3.demo.model.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Date;

public record JuegoRequestDTO(
        String titulo,
        String sinopsis,
        LocalDate fecha_lanzamiento,
        String desarrolladora,
        Float duracion_estimada
) {
}
