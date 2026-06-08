package tp_final_progra3.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.model.dto.request.LoginRequestDto;
import tp_final_progra3.demo.model.dto.response.LoginResponseDto;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PatchMapping("/usuarios/{id}/bloquear")
    public ResponseEntity<UsuarioResponseDTO> bloquearUsuario(@PathVariable Long id){
        return ResponseEntity.ok(adminService.bloquearUsuario(id));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(adminService.loginAdmin(loginRequestDto));
    }

}
