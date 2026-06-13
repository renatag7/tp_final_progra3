package tp_final_progra3.demo.model.dto.request;

public record UpdateReviewRequestDTO(
        String contenido,
        Integer puntuacion,
        Boolean contieneSpoilers
) {
}
