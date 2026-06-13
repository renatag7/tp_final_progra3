package tp_final_progra3.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateUsuarioRequest;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping({"/usuarios"})
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;


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

    @PostMapping("/{usuarioId}/seguir/{seguidoId}")
    public ResponseEntity<UsuarioResponseDTO> follow(@PathVariable Long usuarioId, @PathVariable Long seguidoId){

        return ResponseEntity.ok(usuarioService.follow(usuarioId, seguidoId));
    }

    @DeleteMapping("/{usuarioId}/seguir/{seguidoId}")
    public ResponseEntity<UsuarioResponseDTO> unfollow(@PathVariable Long usuarioId, @PathVariable Long seguidoId){

        return ResponseEntity.ok(usuarioService.unfollow(usuarioId, seguidoId));
    }

    @GetMapping("/{id}/seguidores")
    public ResponseEntity<List<UsuarioResponseDTO>> getFollowers(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.getAllFollowers(id));
    }

    @GetMapping("/{id}/seguidos")
    public ResponseEntity<List<UsuarioResponseDTO>> getFollowed(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.getAllFollowed(id));
    }

    @PostMapping("/{userId}/bloquear/{bloqueadoId}") //POST /usuarios/1/bloquear/2
    public ResponseEntity<UsuarioResponseDTO> bloquearUsuario(@PathVariable Long userId, @PathVariable Long bloqueadoId){

        return ResponseEntity.ok(usuarioService.bloquearUsuario(userId, bloqueadoId));
    }

    @PostMapping("/{userId}/desbloquear/{bloqueadoId}") //POST /usuarios/1/bloquear/2
    public ResponseEntity<UsuarioResponseDTO> desbloquearUsuario(@PathVariable Long userId, @PathVariable Long bloqueadoId){

        return ResponseEntity.ok(usuarioService.desbloquearUsuario(userId, bloqueadoId));
    }
    @GetMapping("/{userId}/bloqueados") // GET /usuarios/1/bloqueados
    public ResponseEntity<List<UsuarioResponseDTO>> verBloqueados(@PathVariable Long userId){

        return ResponseEntity.ok(usuarioService.verUsuariosBloqueados(userId));
    }




}
