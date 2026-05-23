package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.UsuarioExistenteExc;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.dto.request.UsuarioRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDTO create(UsuarioRequestDTO usuarioRequestDTO) throws UsuarioExistenteExc{
        if(this.usuarioRepo.existsByEmail(usuarioRequestDTO.email())){
            throw new UsuarioExistenteExc("El email ingresado ya se encuentra registrado.");
        }
        else if(this.usuarioRepo.existsByUsername(usuarioRequestDTO.username())){
            throw new UsuarioExistenteExc("El nombre de usuario ingresado ya se encuentra registrado.");
        }
        else{
            Usuario usuario = this.usuarioMapper.toEntity(usuarioRequestDTO);
            usuario.setFechaRegistro(LocalDate.now());
            usuario.setRol(Rol.USER);
            usuario.setActivo(true);

            Usuario usuarioGuardado = this.usuarioRepo.save(usuario);
            return this.usuarioMapper.toDTO(usuarioGuardado);
        }
    }


}
