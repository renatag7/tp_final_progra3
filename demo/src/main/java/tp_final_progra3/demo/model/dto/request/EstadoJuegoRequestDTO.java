package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tp_final_progra3.demo.model.enums.Estado;

public record EstadoJuegoRequestDTO(@NotNull(
        message = "El juego es obligatorio"
) Long juego, @NotNull(
        message = "El estado es obligatorio"
) Estado estado
) {
}
