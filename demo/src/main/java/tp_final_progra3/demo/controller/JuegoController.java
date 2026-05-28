package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.service.JuegoApiService;

import java.util.List;

@RestController
@RequestMapping({"/api/juegos"})
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoApiService juegoApiService;

    @GetMapping("/api/search") //ej /juegos/api/search?nombre=elden ring
    public ResponseEntity<List<JuegoResponseDTO>> searchApi(@RequestParam String nombre){

        return ResponseEntity.ok(juegoApiService.findAllGames(nombre));
    }
}
