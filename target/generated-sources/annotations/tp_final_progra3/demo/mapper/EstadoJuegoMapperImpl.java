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
    date = "2026-06-19T18:36:14-0300",
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

        String tituloJuego = null;
        Estado estado = null;
        LocalDate fechaActualizacion = null;

        tituloJuego = estadoJuegoUsuarioJuegoTitulo( estadoJuegoUsuario );
        estado = estadoJuegoUsuario.getEstado();
        fechaActualizacion = estadoJuegoUsuario.getFechaActualizacion();

        EstadoJuegoResponseDTO estadoJuegoResponseDTO = new EstadoJuegoResponseDTO( tituloJuego, estado, fechaActualizacion );

        return estadoJuegoResponseDTO;
    }

    private String estadoJuegoUsuarioJuegoTitulo(EstadoJuegoUsuario estadoJuegoUsuario) {
        Juego juego = estadoJuegoUsuario.getJuego();
        if ( juego == null ) {
            return null;
        }
        return juego.getTitulo();
    }
}
