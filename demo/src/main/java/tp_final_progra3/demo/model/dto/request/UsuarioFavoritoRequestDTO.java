package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UsuarioFavoritoRequestDTO(@NotNull(
        message = "El juego es obligatorio"
) Long juego, @Min(1) @Max(4) @NotNull(
        message = "La posicion es obligatoria"
) Integer posicion
) {
}
