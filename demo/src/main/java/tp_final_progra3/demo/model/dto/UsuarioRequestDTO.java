package tp_final_progra3.demo.model.dto;

import tp_final_progra3.demo.model.enums.Rol;

public record UsuarioRequestDTO(
        String username,
        String nombre,
        String email,
        String password,
        String biografia,
        String pais,
        Boolean perfil_publico,
        Rol rol
) {
}
