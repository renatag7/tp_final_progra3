package tp_final_progra3.demo.model.dto;

public record UsuarioResponseDTO(
        Long id,
        String username,
        String nombre,
        String email,
        String biografia,
        String pais,
        Boolean perfil_publico,
        Integer cantSeguidos,
        Integer cantSeguidores
) {
}
