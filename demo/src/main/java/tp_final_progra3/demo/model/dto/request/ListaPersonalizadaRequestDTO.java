package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListaPersonalizadaRequestDTO(@NotNull(
        message = "El usuario es obligatorio"
) Long usuario, @NotBlank(
        message = "El nombre es obligatorio"
) String nombre, @NotBlank(
        message = "La descripcion es obligatoria"
) String descripcion, @NotNull(
        message = "La privacidad es obligatoria"
) Boolean privacidad

) {
}
