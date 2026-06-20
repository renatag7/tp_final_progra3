package tp_final_progra3.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.UpdateUsuarioRequest;
import tp_final_progra3.demo.model.dto.response.*;
import tp_final_progra3.demo.model.enums.Estado;
import tp_final_progra3.demo.service.*;
import tp_final_progra3.demo.model.dto.response.NotificacionResponseDto;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.UsuarioService;


import java.util.List;

@RestController
@RequestMapping({"/usuarios"})
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ReviewService reviewService;
    private final UsuarioFavoritoService usuarioFavoritoService;
    private final EstadoJuegoUsuarioService estadoJuegoUsuarioService;
    private final ListaPersonalizadaService listaPersonalizadaService;


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

    @PatchMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> updateMiPerfil(Authentication authentication, @Valid @RequestBody UpdateUsuarioRequest usuarioRequest){
        String username = authentication.getName();

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateByUsername(username, usuarioRequest));
    }

    @PostMapping("/seguir/{seguidoId}")
    public ResponseEntity<UsuarioResponseDTO> follow(@PathVariable Long seguidoId, Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(usuarioService.follow(username, seguidoId));
    }

    @DeleteMapping("/seguir/{seguidoId}")
    public ResponseEntity<UsuarioResponseDTO> unfollow(@PathVariable Long seguidoId, Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(usuarioService.unfollow(username, seguidoId));
    }

    @GetMapping("/{id}/seguidores")
    public ResponseEntity<List<UsuarioResponseDTO>> getFollowers(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.getAllFollowers(id));
    }

    @GetMapping("/{id}/seguidos")
    public ResponseEntity<List<UsuarioResponseDTO>> getFollowed(@PathVariable Long id){

        return ResponseEntity.ok(usuarioService.getAllFollowed(id));
    }

    @PostMapping("/bloquear/{bloqueadoId}")
    public ResponseEntity<Void> bloquearUsuario(@PathVariable Long bloqueadoId, Authentication authentication){
        usuarioService.bloquearUsuario(authentication.getName(), bloqueadoId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desbloquear/{bloqueadoId}")
    public ResponseEntity<Void> desbloquearUsuario(@PathVariable Long bloqueadoId, Authentication authentication){
        usuarioService.desbloquearUsuario(authentication.getName(), bloqueadoId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/me/bloqueados") // GET /usuarios/1/bloqueados
    public ResponseEntity<List<UsuarioResponseDTO>> verBloqueados(Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(usuarioService.verUsuariosBloqueados(username));
    }
    @GetMapping("/me/notificaciones")
    public ResponseEntity<List<NotificacionResponseDto>> verNotificaciones(Authentication authentication){
        return ResponseEntity.ok(usuarioService.verNotificaciones(authentication.getName()));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(@PathVariable Long id, Authentication authentication){
        return ResponseEntity.ok(reviewService.getReviewsByUsuario(id, authentication.getName()));
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getMiPerfil(Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok(usuarioService.getByUsername(username));
    }

    @PostMapping("/me/favoritos/{juegoId}")
    public ResponseEntity<List<FavoritoResponseDTO>> agregarFavorito(@PathVariable Long juegoId, @RequestParam Integer posicion, Authentication authentication){

        return ResponseEntity.ok(usuarioFavoritoService.agregarFavorito(authentication.getName(), juegoId, posicion));
    }

    @GetMapping("/me/favoritos")
    public ResponseEntity<List<FavoritoResponseDTO>> getFavoritos(Authentication authentication){
        return ResponseEntity.ok(usuarioFavoritoService.getFavoritos(authentication.getName()));
    }

    @DeleteMapping("/me/favoritos/{juegoId}")
    public ResponseEntity<Void> deleteFavorito(@PathVariable Long juegoId, Authentication authentication){
        usuarioFavoritoService.eliminarFavorito(authentication.getName(), juegoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/historial")
    public ResponseEntity<List<EstadoJuegoResponseDTO>> getMiHistorial(@RequestParam(required = false) Estado estado, Authentication authentication){

        return ResponseEntity.ok(estadoJuegoUsuarioService.getHistorialUsuario(authentication.getName(), estado));
    }

    @GetMapping("/me/listas")
    public ResponseEntity<List<ListaPersonalizadaResponseDTO>> misListas(
            Authentication authentication) {

        return ResponseEntity.ok(listaPersonalizadaService.verMisListas(authentication.getName()));
    }

    @PatchMapping("/me/notificaciones/{id}/leer")
    public ResponseEntity<NotificacionResponseDto> marcarComoLeida(@PathVariable Long id, Authentication authentication) {
        NotificacionResponseDto notificacionResponseDto = usuarioService.marcarNotificacionComoLeida(id, authentication.getName());

        return ResponseEntity.ok(notificacionResponseDto);
    }

}
