package tp_final_progra3.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.LoginRequestDto;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.LoginResponseDto;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PatchMapping("/usuarios/{id}/bloquear")
    public ResponseEntity<UsuarioResponseDTO> bloquearUsuario(@PathVariable Long id){
        return ResponseEntity.ok(adminService.bloquearUsuario(id));
    }

    @PatchMapping("/usuarios/{id}/desbloquear")
    public ResponseEntity<UsuarioResponseDTO> habilitarUsuario(@PathVariable Long id){
        return ResponseEntity.ok(adminService.rehabilitarUsuario(id));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(adminService.loginAdmin(loginRequestDto));
    }

    @DeleteMapping("/reviews/{id}") // DELETE /api/admin/reviews/5
    public ResponseEntity<Void > eliminarReview (@PathVariable Long id ){
        adminService.eliminarReview(id);
        return ResponseEntity.noContent().build();  // operacion exitosa, no devuelve nada
    }

    @PatchMapping("/juegos/{id}/deshabilitar")
    public ResponseEntity<JuegoResponseDTO> deshabilitarJuego(@PathVariable Long id){
        return ResponseEntity.ok(adminService.deshabilitarJuego(id));
    }

    @PatchMapping("/juegos/{id}/rehabilitar")
    public ResponseEntity<JuegoResponseDTO> rehabilitarJuego(@PathVariable Long id){
        return ResponseEntity.ok(adminService.rehabilitarJuego(id));
    }

    @GetMapping("/usuarios") // GET /api/admin/usuarios
    public ResponseEntity<List<UsuarioResponseDTO>>  VerUsuarios ( ){
        return ResponseEntity.ok (adminService.verTodosLosUsuarios());
    }



}
