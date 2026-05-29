package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.UsuarioRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Usuario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-28T18:41:16-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO) {
        if ( usuarioRequestDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsername( usuarioRequestDTO.username() );
        usuario.setNombre( usuarioRequestDTO.nombre() );
        usuario.setEmail( usuarioRequestDTO.email() );
        usuario.setPassword( usuarioRequestDTO.password() );
        usuario.setBiografia( usuarioRequestDTO.biografia() );
        usuario.setPais( usuarioRequestDTO.pais() );
        if ( usuarioRequestDTO.perfilPublico() != null ) {
            usuario.setPerfilPublico( usuarioRequestDTO.perfilPublico() );
        }

        return usuario;
    }

    @Override
    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String username = null;
        String nombre = null;
        String biografia = null;
        String pais = null;

        username = usuario.getUsername();
        nombre = usuario.getNombre();
        biografia = usuario.getBiografia();
        pais = usuario.getPais();

        Long id = null;
        Boolean perfil_publico = null;
        LocalDate fecha_registro = null;
        Integer cantSeguidos = null;
        Integer cantSeguidores = null;

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( id, username, nombre, biografia, pais, perfil_publico, fecha_registro, cantSeguidos, cantSeguidores );

        return usuarioResponseDTO;
    }
}
