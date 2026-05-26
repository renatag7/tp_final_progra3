package tp_final_progra3.demo.model.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;


public record JuegoRequestDTO(@NotBlank(
        message = "El titulo es obligatorio"
) String titulo, @NotBlank(
        message = "La sinopsis es obligatoria"
) String sinopsis, @NotNull(
        message = "La fecha de lanzamiento es obligatoria"
) LocalDate fecha_lanzamiento, @NotBlank(
        message = "El developer es obligatorio"
) String developer, @NotEmpty(
        message = "Debe haber al menos un género"
) List<String> generos,
  List<String> plataformas
) {
}
