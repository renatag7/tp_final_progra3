package tp_final_progra3.demo.security;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO( //para iniciar sesión
        @NotBlank String username,
        @NotBlank String password
) {
}
