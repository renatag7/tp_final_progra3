package tp_final_progra3.demo.model.dto.response;

import tp_final_progra3.demo.model.enums.Rol;

public record LoginResponseDto(
        Long id,
        String nombreUsuario,
        Rol rol
) {
}
