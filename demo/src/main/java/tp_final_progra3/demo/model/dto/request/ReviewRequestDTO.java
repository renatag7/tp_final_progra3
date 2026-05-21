package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequestDTO(@NotNull(
        message = "El usuario es obligatorio"
) Long usuario, @NotNull(
        message = "El juego es obligatorio"
) Long juego, @NotBlank(
        message = "El contenido es obligatorio"
) String contenido,
  Float puntuacion, @NotNull(
          message = "Campo 'contiene spoilers' es obligatorio"
) Boolean contieneSpoilers
) {
}
