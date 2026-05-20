package tp_final_progra3.demo.model.dto;

public record ReviewRequestDTO(
        Long usuario,
        Long juego,
        String contenido,
        Float puntuacion,
        Boolean contieneSpoilers
) {
}
