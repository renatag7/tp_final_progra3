package tp_final_progra3.demo.exceptions.security;

public record AuthResponseDTO(
        String tokenType,
        String accessToken
) {
}
