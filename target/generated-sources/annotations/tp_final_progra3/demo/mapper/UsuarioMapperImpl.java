package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Usuario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-17T21:40:13-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(RegisterRequestDTO registerRequestDTO) {
        if ( registerRequestDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.username( registerRequestDTO.username() );
        usuario.nombre( registerRequestDTO.nombre() );
        usuario.email( registerRequestDTO.email() );
        usuario.password( registerRequestDTO.password() );
        usuario.biografia( registerRequestDTO.biografia() );
        usuario.pais( registerRequestDTO.pais() );
        if ( registerRequestDTO.perfilPublico() != null ) {
            usuario.perfilPublico( registerRequestDTO.perfilPublico() );
        }

        return usuario.build();
    }

    @Override
    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String nombre = null;
        String biografia = null;
        String pais = null;
        Boolean perfilPublico = null;
        LocalDate fechaRegistro = null;

        id = usuario.getIdUsuario();
        username = usuario.getUsername();
        nombre = usuario.getNombre();
        biografia = usuario.getBiografia();
        pais = usuario.getPais();
        perfilPublico = usuario.isPerfilPublico();
        fechaRegistro = usuario.getFechaRegistro();

        Integer cantSeguidos = usuario.getSeguidos() != null ? usuario.getSeguidos().size() : 0;
        Integer cantSeguidores = usuario.getSeguidores() != null ? usuario.getSeguidores().size() : 0;

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( id, username, nombre, biografia, pais, perfilPublico, fechaRegistro, cantSeguidos, cantSeguidores );

        return usuarioResponseDTO;
    }
}
