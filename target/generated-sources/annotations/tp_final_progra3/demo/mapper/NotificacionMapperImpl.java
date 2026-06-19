package tp_final_progra3.demo.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.response.NotificacionResponseDto;
import tp_final_progra3.demo.model.entity.Notificacion;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-19T19:12:44-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class NotificacionMapperImpl implements NotificacionMapper {

    @Override
    public NotificacionResponseDto toDto(Notificacion notificacion) {
        if ( notificacion == null ) {
            return null;
        }

        Long id = null;
        String mensaje = null;
        String fecha = null;
        Boolean leida = null;

        id = notificacion.getId();
        mensaje = notificacion.getMensaje();
        fecha = mapFecha( notificacion.getFecha() );
        leida = notificacion.getLeida();

        NotificacionResponseDto notificacionResponseDto = new NotificacionResponseDto( id, mensaje, fecha, leida );

        return notificacionResponseDto;
    }
}
