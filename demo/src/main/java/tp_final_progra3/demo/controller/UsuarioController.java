package tp_final_progra3.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.UpdateUsuarioRequest;
import tp_final_progra3.demo.model.dto.request.UsuarioRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping({"/api/usuarios"})
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO){
        UsuarioResponseDTO usuario = usuarioService.create(usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsers(){
        List<UsuarioResponseDTO> users = this.usuarioService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Long id){
        UsuarioResponseDTO usuarioResponseDTO = this.usuarioService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponseDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioResponseDTO> getByUsername(@PathVariable String username){
        UsuarioResponseDTO usuarioResponseDTO = this.usuarioService.getByUsername(username);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UpdateUsuarioRequest usuarioRequest){
        UsuarioResponseDTO usuarioResponseDTO = this.usuarioService.update(id, usuarioRequest);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioResponseDTO);
    }
}
