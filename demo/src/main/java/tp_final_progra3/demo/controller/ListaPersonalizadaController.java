package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.service.ListaPersonalizadaService;

@RequestMapping("/listas")
@RestController
@RequiredArgsConstructor
public class ListaPersonalizadaController {
    private final ListaPersonalizadaService listaPersonalizadaService;

    @PostMapping
    public ResponseEntity<ListaPersonalizadaResponseDTO>  crearLista (@RequestBody ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(listaPersonalizadaService.crearLista(listaPersonalizadaRequestDTO));
    }
}
