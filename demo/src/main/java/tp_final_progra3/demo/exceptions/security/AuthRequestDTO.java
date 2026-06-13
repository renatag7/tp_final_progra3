package tp_final_progra3.demo.exceptions.security;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO( //para iniciar sesión
        @NotBlank String username,
        @NotBlank String password
) {
}
