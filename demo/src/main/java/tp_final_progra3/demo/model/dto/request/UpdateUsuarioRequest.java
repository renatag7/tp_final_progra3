package tp_final_progra3.demo.model.dto.request;

public record UpdateUsuarioRequest(
        String username,
        String nombre,
        String biografia,
        String pais,
        Boolean perfilPublico
) {
}
