package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.dto.request.UsuarioRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO);
    UsuarioResponseDTO toDTO(Usuario usuario);
}
