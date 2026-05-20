package tp_final_progra3.demo.model.dto;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        Long usuario,
        Long juego,
        String contenido,
        Float puntuacion,
        LocalDateTime fechaPublicacion,
        Boolean contieneSpoilers
) {
}
