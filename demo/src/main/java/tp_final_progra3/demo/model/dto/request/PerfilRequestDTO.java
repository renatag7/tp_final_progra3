package tp_final_progra3.demo.model.dto.request;

public record PerfilRequestDTO( //para editar el perfil
        String nombre,
        String biografia,
        String pais,
        Boolean perfilPublico
) {
}
