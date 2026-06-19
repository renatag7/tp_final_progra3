package tp_final_progra3.demo.model.dto.response;

import java.time.LocalDateTime;

public record NotificacionResponseDto(
        Long id,
        String mensaje,
        String fecha,
        Boolean leida
) {
}

