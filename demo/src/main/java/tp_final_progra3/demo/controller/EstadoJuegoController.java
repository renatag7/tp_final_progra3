package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.service.EstadoJuegoUsuarioService;

@RestController
@RequestMapping ("/estado-juegos")
@RequiredArgsConstructor
public class EstadoJuegoController {

    private final EstadoJuegoUsuarioService estadoJuegoUsuarioService;

    public ResponseEntity<EstadoJuegoResponseDTO> crearEstado (@PathVariable Long usuarioId, @RequestBody EstadoJuegoRequestDTO estadoJuegoRequestDTO){
        return ResponseEntity.ok(estadoJuegoUsuarioService.crearEstado(usuarioId, estadoJuegoRequestDTO));
    }


}
