package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequestDTO(@NotBlank(
        message = "El contenido es obligatorio"
) String contenido, @NotNull(
        message = "La puntuación es obligatoria"
) @Min(1) @Max(5)
  Float puntuacion, @NotNull(
          message = "Campo 'contiene spoilers' es obligatorio"
) Boolean contieneSpoilers
) {
}
