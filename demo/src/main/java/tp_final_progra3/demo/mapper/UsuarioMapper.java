package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(RegisterRequestDTO registerRequestDTO);

    @Mapping(source = "idUsuario", target = "id")
    @Mapping(target = "cantSeguidos",
            expression = "java(usuario.getSeguidos() != null ? usuario.getSeguidos().size() : 0)")
    @Mapping(target = "cantSeguidores",
            expression = "java(usuario.getSeguidores() != null ? usuario.getSeguidores().size() : 0)")
    UsuarioResponseDTO toDTO(Usuario usuario);

}
