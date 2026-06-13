package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;

@Mapper(componentModel = "spring" )
public interface EstadoJuegoMapper {

@Mapping(target = "id_estado", ignore = true)
@Mapping(target = "usuario", ignore = true)
@Mapping(target = "juego", ignore = true)
@Mapping(target = "fecha_actualizacion", ignore = true)
    EstadoJuegoUsuario ToEntity(EstadoJuegoRequestDTO estadoJuegoRequestDTO);
@Mapping(target = "juego", source = "juego.id")
    EstadoJuegoResponseDTO ToDto (EstadoJuegoUsuario estadoJuegoUsuario);

}
