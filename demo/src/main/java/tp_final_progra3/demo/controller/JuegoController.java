package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.exceptions.JuegoNoExisteExc;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.service.JuegoApiService;

import java.util.List;

@RestController
@RequestMapping({"/juegos"})
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoApiService juegoApiService;

    @GetMapping("/api/search") //ej /juegos/api/search?nombre=elden ring
    public ResponseEntity<List<JuegoResponseDTO>> searchApi(@RequestParam String nombre){

        return ResponseEntity.ok(juegoApiService.findAllGames(nombre));
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<JuegoResponseDTO> getById(@PathVariable Long id){
        JuegoResponseDTO juegoResponseDTO = this.juegoApiService.getJuegoById(id);

        return ResponseEntity.status(HttpStatus.OK).body(juegoResponseDTO);
    }

}
