package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;
import tp_final_progra3.demo.model.dto.response.NotificacionResponseDto;
import tp_final_progra3.demo.model.entity.Notificacion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface NotificacionMapper {

    NotificacionResponseDto toDto(Notificacion notificacion);

    default String mapFecha(LocalDateTime fecha) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return fecha.format(formatter);
    }
}
