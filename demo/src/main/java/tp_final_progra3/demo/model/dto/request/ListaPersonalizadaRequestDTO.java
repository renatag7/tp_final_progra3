package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ListaPersonalizadaRequestDTO(@NotBlank(
        message = "El nombre es obligatorio"
) @Size(max = 50) String nombre, @Size(max = 250
) String descripcion, @NotNull(
        message = "El nivel de privacidad es obligatorio"
) Boolean esPublica

) {
}
