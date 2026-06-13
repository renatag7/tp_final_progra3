package tp_final_progra3.demo.security;

public record AuthResponseDTO(
        String tokenType,
        String accessToken
) {
}
