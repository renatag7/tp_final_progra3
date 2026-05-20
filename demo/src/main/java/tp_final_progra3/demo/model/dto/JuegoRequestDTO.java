package tp_final_progra3.demo.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record JuegoRequestDTO(@NotBlank(
        message = "El titulo es obligatorio"
) String titulo, @NotBlank(
        message = "La sinopsis es obligatoria"
) String sinopsis, @NotNull(
        message = "La fecha de lanzamiento es obligatoria"
) LocalDate fecha_lanzamiento, @NotBlank(
        message = "La desarrolladora es obligatoria"
) String desarrolladora, @NotNull(
        message = "La duración es obligatoria"
) Float duracion_estimada
) {
}
