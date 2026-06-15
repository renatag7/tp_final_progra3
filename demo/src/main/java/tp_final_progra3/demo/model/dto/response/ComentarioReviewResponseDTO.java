package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDate;

public record ComentarioReviewResponseDTO(
        Long id,
        String contenido,
        String username,
        LocalDate fechaPublicado
) {
}
