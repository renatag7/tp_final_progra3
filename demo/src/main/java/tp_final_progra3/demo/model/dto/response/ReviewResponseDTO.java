package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        String username,
        String tituloJuego,
        String contenido,
        Float puntuacion,
        LocalDateTime fechaPublicacion,
        Boolean contieneSpoilers
) {
}
