package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.UsuarioNoExisteExc;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.request.LoginRequestDto;
import tp_final_progra3.demo.model.dto.response.LoginResponseDto;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Review;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.ReviewRepository;
import tp_final_progra3.demo.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class AdminService {
        private final UsuarioRepository usuarioRepository;
        private final ReviewRepository reviewRepository;
        private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO bloquearUsuario(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoExisteExc("Usuario no encontrado"));

        usuario.setActivo(false);

        usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public LoginResponseDto loginAdmin(LoginRequestDto loginRequestDto){

        Usuario usuario = usuarioRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(usuario.getRol() != Rol.ADMIN){
            throw new RuntimeException("No es administrador");
        }

        if(!usuario.getPassword().equals(loginRequestDto.contraseña())){
            throw new RuntimeException("Contraseña incorrecta");
        }

       return new LoginResponseDto(usuario.getId_usuario(), usuario.getUsername(), usuario.getRol()); // mapeo manual porque son 3 campos
    }

    public void eliminarReview(Long id){

        Review review = reviewRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("Review no encontrada"));

        reviewRepository.delete(review);
    }


}







