package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ComentarioReviewRequestDTO(
        @NotBlank(message = "El comentario no puede estar vacío")
        String contenido
) {
}
