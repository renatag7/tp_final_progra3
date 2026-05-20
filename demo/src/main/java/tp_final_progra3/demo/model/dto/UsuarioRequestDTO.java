package tp_final_progra3.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tp_final_progra3.demo.model.enums.Rol;

public record UsuarioRequestDTO(@NotBlank(
        message = "El username es obligatorio"
) String username, @NotBlank(
        message = "El nombre es obligatorio"
) String nombre, @Email(
        message = "El email debe tener un formato válido"
) @NotBlank(
        message = "El email es obligatorio"
) String email, @NotBlank(
        message = "La contraseña es obligatoria"
) String password,
  String biografia,
  String pais, @NotNull(
        message = "El estado del perfil es obligatorio"
) Boolean perfil_publico, @NotNull(
        message = "El rol es obligatorio"
) Rol rol

) {
}
