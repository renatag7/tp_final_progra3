package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstadoJuegoRequestDTO(@NotNull(
        message = "El juego es obligatorio"
) Long juego, @NotBlank(
        message = "El estado es obligatorio"
) String estado
) {
}
