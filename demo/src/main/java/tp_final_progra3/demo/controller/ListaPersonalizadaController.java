package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.service.ListaPersonalizadaService;

import java.util.List;

@RequestMapping("/listas")
@RestController
@RequiredArgsConstructor
public class ListaPersonalizadaController {
    private final ListaPersonalizadaService listaPersonalizadaService;

    @PostMapping
    public ResponseEntity<ListaPersonalizadaResponseDTO>  crearLista (@RequestBody ListaPersonalizadaRequestDTO requestDTO, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(listaPersonalizadaService.crearLista(requestDTO, authentication.getName()));
    }

    @PostMapping("/{idLista}/juegos/{idJuego}")
    public ResponseEntity<ListaPersonalizadaResponseDTO> agregarJuego(@PathVariable Long idLista, @PathVariable Long idJuego, Authentication authentication){

        return ResponseEntity.ok(listaPersonalizadaService.agregarJuegoALista(idLista, idJuego, authentication.getName()));
    }
    @DeleteMapping("/{idLista}/juegos/{idJuego}") //DELETE /listas/1/juegos/5
    public ResponseEntity<ListaPersonalizadaResponseDTO> eliminarJuego(@PathVariable Long idLista, @PathVariable Long idJuego, Authentication authentication){

        return ResponseEntity.ok(listaPersonalizadaService.eliminarJuegoDeLista(idLista, idJuego, authentication.getName()));
    }

    @GetMapping("/publica/usuario/{idUsuario}")
    public ResponseEntity<List<ListaPersonalizadaResponseDTO>> verListaPublicadeOtroUsuario (@PathVariable Long idUsuario, Authentication authentication){
        return ResponseEntity.ok(listaPersonalizadaService.verListasDeOtroUsuario(idUsuario, authentication.getName()));
    }


}
