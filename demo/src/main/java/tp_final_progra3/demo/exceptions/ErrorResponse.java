package tp_final_progra3.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String mensaje;
    private String detalle;

    public ErrorResponse(String mensaje, String detalle) {
        this.timestamp = LocalDateTime.now();
        this.mensaje = mensaje;
        this.detalle = detalle;
    }
}
