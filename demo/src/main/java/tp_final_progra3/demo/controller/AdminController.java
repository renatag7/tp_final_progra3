package tp_final_progra3.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PatchMapping("/usuarios/{id}/bloquear")
    public ResponseEntity<UsuarioResponseDTO> bloquearUsuario(@PathVariable Long id){
        return ResponseEntity.ok(adminService.bloquearUsuario(id));
    }

    @GetMapping("/usuarios/bloqueados")
    public ResponseEntity<List<UsuarioResponseDTO>> verUsuariosBloqueados(){

        return ResponseEntity.ok(adminService.verUsuariosBloqueados());
    }

    @PatchMapping("/usuarios/{id}/desbloquear")
    public ResponseEntity<UsuarioResponseDTO> desbloquearUsuario(@PathVariable Long id){
        return ResponseEntity.ok(adminService.rehabilitarUsuario(id));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void > eliminarReview (@PathVariable Long id ){
        adminService.eliminarReview(id);
        return ResponseEntity.noContent().build();  // operacion exitosa, no devuelve nada
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id){
        adminService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/juegos/{id}/deshabilitar")
    public ResponseEntity<JuegoResponseDTO> deshabilitarJuego(@PathVariable Long id){
        return ResponseEntity.ok(adminService.deshabilitarJuego(id));
    }

    @PatchMapping("/juegos/{id}/rehabilitar")
    public ResponseEntity<JuegoResponseDTO> rehabilitarJuego(@PathVariable Long id){
        return ResponseEntity.ok(adminService.rehabilitarJuego(id));
    }

    @GetMapping("/usuarios") // GET /admin/usuarios
    public ResponseEntity<List<UsuarioResponseDTO>>  VerUsuarios ( ){
        return ResponseEntity.ok (adminService.verTodosLosUsuarios());
    }

    @GetMapping("/usuarios/buscar") // /buscar?username=renatag7
    public ResponseEntity<List<UsuarioResponseDTO>> filtrarUsername ( @RequestParam String username ){
        return ResponseEntity.ok(adminService.filtrarPorNombreUsuario(username));
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> verTodasLasReviews(){
        return ResponseEntity.ok(adminService.verTodasLasReviews());
    }

    @GetMapping("/comentarios")
    public ResponseEntity<List<ComentarioReviewResponseDTO>> verTodosLosComentarios(){
        return ResponseEntity.ok(adminService.verTodosLosComentarios());
    }

    @GetMapping("/juegos/{id}")
    public ResponseEntity<JuegoResponseDTO> getById(@PathVariable Long id){
        JuegoResponseDTO juegoResponseDTO = adminService.getJuegoByIdAdmin(id);

        return ResponseEntity.status(HttpStatus.OK).body(juegoResponseDTO);
    }

}
