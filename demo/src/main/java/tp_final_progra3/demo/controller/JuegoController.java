package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.CompraResponseDto;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.service.EstadoJuegoUsuarioService;
import tp_final_progra3.demo.service.JuegoApiService;
import tp_final_progra3.demo.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping({"/juegos"})
@RequiredArgsConstructor
public class JuegoController {
    private final JuegoApiService juegoApiService;
    private final ReviewService reviewService;
    private final EstadoJuegoUsuarioService estadoJuegoUsuarioService;

    @GetMapping("/api/search") //ej /juegos/api/search?nombre=elden ring
    public ResponseEntity<List<JuegoResponseDTO>> searchApi(@RequestParam String nombre){

        return ResponseEntity.ok(juegoApiService.findAllGames(nombre));
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<JuegoResponseDTO> getById(@PathVariable Long id){
        JuegoResponseDTO juegoResponseDTO = this.juegoApiService.getJuegoById(id);

        return ResponseEntity.status(HttpStatus.OK).body(juegoResponseDTO);
    }


    @GetMapping("/api/genero")// GET /juegos/api/genero?genero=action
    public ResponseEntity<List<JuegoResponseDTO>> filtrarPorGenero (@RequestParam  String genero){
        return ResponseEntity.ok(juegoApiService.filtrarJuegosGenero(genero));
    }

    @GetMapping("/api/{id}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByJuego(@PathVariable Long id, Authentication authentication){

        return ResponseEntity.ok(reviewService.getReviewsByJuego(id, authentication.getName()));
    }

    @PostMapping("/api/{juegoId}/estado")
    public ResponseEntity<EstadoJuegoResponseDTO> actualizarEstado(@PathVariable Long juegoId, @RequestBody EstadoJuegoRequestDTO estadoJuegoRequestDTO, Authentication authentication){
        return ResponseEntity.ok(estadoJuegoUsuarioService.actualizarEstado(juegoId, estadoJuegoRequestDTO, authentication.getName()));
    }

    @GetMapping("/api/{id}/comprar")
    public ResponseEntity<CompraResponseDto> enlaceCompra(@PathVariable Long id){
        return ResponseEntity.ok(juegoApiService.enlaceCompra(id));
    }

}
