package tp_final_progra3.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoDuplicadoExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.exceptions.usuarios.CredencialesInvalidasExc;
import tp_final_progra3.demo.exceptions.usuarios.CuentaRestringidaExc;
import tp_final_progra3.demo.exceptions.videojuegos.JuegoDeshabilitadoExc;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoExc.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RecursoNoEncontradoExc exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(exc.getMessage(), "NOT_FOUND"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(errores, "VALIDATION_ERROR"));
    }

    @ExceptionHandler(OperacionNoPermitidaExc.class)
    public ResponseEntity<ErrorResponse> handleOperacionNoPermitida(OperacionNoPermitidaExc ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ex.getMessage(), "FORBIDDEN"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "Error interno del servidor",
                        "INTERNAL_SERVER_ERROR"
                ));
    }

    @ExceptionHandler(JuegoDeshabilitadoExc.class)
    public ResponseEntity<ErrorResponse> handleJuegoDeshabilitado(JuegoDeshabilitadoExc ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ex.getMessage(), "BAD_REQUEST"));
    }

    @ExceptionHandler(RecursoDuplicadoExc.class)
    public ResponseEntity<ErrorResponse> handleRecursoDuplicado(RecursoDuplicadoExc ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage(), "CONFLICT"));
    }

    @ExceptionHandler(CuentaRestringidaExc.class)
    public ResponseEntity<ErrorResponse> handleCuentaRestringida(CuentaRestringidaExc ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(ex.getMessage(), "FORBIDDEN"));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Usuario o contraseña incorrectos", "UNAUTHORIZED"));
    }

}
