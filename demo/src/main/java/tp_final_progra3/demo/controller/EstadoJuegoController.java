package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.HistorialJuegoResponseDTO;
import tp_final_progra3.demo.service.EstadoJuegoUsuarioService;

import java.util.List;

@RestController
@RequestMapping ("/estado-juegos")
@RequiredArgsConstructor
public class EstadoJuegoController {

    private final EstadoJuegoUsuarioService estadoJuegoUsuarioService;

    public ResponseEntity<EstadoJuegoResponseDTO> crearEstado (@PathVariable Long usuarioId, @RequestBody EstadoJuegoRequestDTO estadoJuegoRequestDTO){
        return ResponseEntity.ok(estadoJuegoUsuarioService.crearEstado(usuarioId, estadoJuegoRequestDTO));
    }

    @GetMapping("/me/historial")
    public ResponseEntity<List<HistorialJuegoResponseDTO>> getMiHistorial(Authentication authentication){

        return ResponseEntity.ok(estadoJuegoUsuarioService.getHistorialUsuario(authentication.getName()));
    }


}
