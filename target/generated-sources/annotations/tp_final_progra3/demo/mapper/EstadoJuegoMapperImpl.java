package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.enums.Estado;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-13T14:28:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class EstadoJuegoMapperImpl implements EstadoJuegoMapper {

    @Override
    public EstadoJuegoUsuario ToEntity(EstadoJuegoRequestDTO estadoJuegoRequestDTO) {
        if ( estadoJuegoRequestDTO == null ) {
            return null;
        }

        EstadoJuegoUsuario estadoJuegoUsuario = new EstadoJuegoUsuario();

        estadoJuegoUsuario.setEstado( estadoJuegoRequestDTO.estado() );

        return estadoJuegoUsuario;
    }

    @Override
    public EstadoJuegoResponseDTO ToDto(EstadoJuegoUsuario estadoJuegoUsuario) {
        if ( estadoJuegoUsuario == null ) {
            return null;
        }

        Long juego = null;
        Estado estado = null;
        LocalDate fecha_actualizacion = null;

        juego = estadoJuegoUsuarioJuegoId( estadoJuegoUsuario );
        estado = estadoJuegoUsuario.getEstado();
        fecha_actualizacion = estadoJuegoUsuario.getFecha_actualizacion();

        Long id = null;

        EstadoJuegoResponseDTO estadoJuegoResponseDTO = new EstadoJuegoResponseDTO( id, juego, estado, fecha_actualizacion );

        return estadoJuegoResponseDTO;
    }

    private Long estadoJuegoUsuarioJuegoId(EstadoJuegoUsuario estadoJuegoUsuario) {
        Juego juego = estadoJuegoUsuario.getJuego();
        if ( juego == null ) {
            return null;
        }
        return juego.getId();
    }
}
