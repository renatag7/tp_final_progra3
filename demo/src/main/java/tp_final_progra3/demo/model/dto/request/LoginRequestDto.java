package tp_final_progra3.demo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @Email (message = "el mail debe tener un formato valido")
        @NotBlank(message = "El mail es obligatorio")
        String email,

        @NotBlank(message = "la contraseña es obligatoria")
        String contraseña

) {
}
