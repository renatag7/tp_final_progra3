package tp_final_progra3.demo.model.dto.request;

public record UpdateReviewRequestDTO(
        String contenido,
        Float puntuacion,
        Boolean contieneSpoilers
) {
}
