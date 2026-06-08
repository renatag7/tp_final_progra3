package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.SeguimientoExc;
import tp_final_progra3.demo.exceptions.UsuarioExistenteExc;
import tp_final_progra3.demo.exceptions.UsuarioNoExisteExc;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.request.UpdateUsuarioRequest;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.dto.request.UsuarioRequestDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    public List<UsuarioResponseDTO> getAllUsers(){
        List<Usuario> usuarios = usuarioRepo.findByRol(Rol.USER);

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public Usuario getUserById(Long id){
        return this.usuarioRepo.findById(id).orElseThrow(() -> new UsuarioNoExisteExc("Usuario no encontrado."));
    }

    public UsuarioResponseDTO getById(Long id){
        Usuario usuario = this.getUserById(id);
        return this.usuarioMapper.toDTO(usuario);
    }

    public Usuario getUserByUsername(String username){
        return this.usuarioRepo.findByUsername(username).orElseThrow(() -> new UsuarioNoExisteExc("Usuario no encontrado."));
    }

    public UsuarioResponseDTO getByUsername(String username){
        Usuario usuario = this.getUserByUsername(username);
        return this.usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO update(Long id, UpdateUsuarioRequest usuarioRequest){
        Usuario usuario = getUserById(id);

        if(usuarioRequest.username() != null){
            usuario.setUsername(usuarioRequest.username());
        }
        if(usuarioRequest.nombre() != null){
            usuario.setNombre(usuarioRequest.nombre());
        }
        if(usuarioRequest.biografia() != null){
            usuario.setBiografia(usuarioRequest.biografia());
        }
        if(usuarioRequest.pais() != null){
            usuario.setPais(usuarioRequest.pais());
        }
        if(usuarioRequest.perfilPublico() != null){
            usuario.setPerfilPublico(usuarioRequest.perfilPublico());
        }

        Usuario actualizado = usuarioRepo.save(usuario);
        return usuarioMapper.toDTO(actualizado);
    }

    public UsuarioResponseDTO follow(Long userId, Long seguidoId){
        Usuario usuario = getUserById(userId);
        Usuario seguido = getUserById(seguidoId);

        if(usuario.equals(seguido)){
            throw new SeguimientoExc("No puede seguirse a uno mismo.");
        }

        if(usuario.getSeguidos().contains(seguido)){
            throw new SeguimientoExc("Ya sigue a este usuario");
        }

        usuario.getSeguidos().add(seguido);
        usuarioRepo.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO unfollow(Long userId, Long seguidoId){
        Usuario usuario = getUserById(userId);
        Usuario seguido = getUserById(seguidoId);

        if(!usuario.getSeguidos().contains(seguido)){
            throw new SeguimientoExc("No sigue a este usuario");
        }

        usuario.getSeguidos().remove(seguido);
        usuarioRepo.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public List<UsuarioResponseDTO> getAllFollowers(Long userId){
        Usuario usuario = getUserById(userId);

        return usuario.getSeguidores().stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public List<UsuarioResponseDTO> getAllFollowed(Long userId){
        Usuario usuario = getUserById(userId);

        return usuario.getSeguidos().stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }
}
