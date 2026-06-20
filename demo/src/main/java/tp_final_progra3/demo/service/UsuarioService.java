package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoDuplicadoExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.NotificacionMapper;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateUsuarioRequest;
import tp_final_progra3.demo.model.dto.response.NotificacionResponseDto;
import tp_final_progra3.demo.model.entity.Notificacion;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.NotificacionRepository;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepo;
    private final UsuarioMapper usuarioMapper;
    private final NotificacionRepository notificacionRepository;
    private final NotificacionMapper notificacionMapper;


    public List<UsuarioResponseDTO> getAllUsers(){
        List<Usuario> usuarios = usuarioRepo.findByRol(Rol.USER);

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public Usuario getUserById(Long id){
        return this.usuarioRepo.findById(id).orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado."));
    }

    public UsuarioResponseDTO getById(Long id){
        Usuario usuario = this.getUserById(id);
        return this.usuarioMapper.toDTO(usuario);
    }

    public Usuario getUserByUsername(String username){
        return this.usuarioRepo.findByUsername(username).orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado."));
    }

    public UsuarioResponseDTO getByUsername(String username){
        Usuario usuario = this.getUserByUsername(username);
        return this.usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO updateByUsername(String username, UpdateUsuarioRequest usuarioRequest){
        Usuario usuario = getUserByUsername(username);

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

    public UsuarioResponseDTO follow(String username, Long seguidoId){
        Usuario usuario = getUserByUsername(username);
        Usuario seguido = getUserById(seguidoId);

        if(usuario.equals(seguido)){
            throw new OperacionNoPermitidaExc("No puede seguirse a uno mismo.");
        }

        if(usuario.getSeguidos().contains(seguido)){
            throw new OperacionNoPermitidaExc("Ya sigue a este usuario");
        }

        if(usuario.getUsuariosBloqueados().contains(seguido)){
            throw new RuntimeException("No puedes seguir a un usuario que bloqueaste.");
        }

        if(seguido.getUsuariosBloqueados().contains(usuario)){
            throw new RuntimeException("este usuario te ha bloqueado.");
        }

        usuario.getSeguidos().add(seguido);
        usuarioRepo.save(usuario);

        Notificacion notificacion = new Notificacion();

        notificacion.setUsuario(seguido);
        notificacion.setMensaje(usuario.getUsername() + " comenzó a seguirte");
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeida(false);

        notificacionRepository.save(notificacion);

        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO unfollow(String username, Long seguidoId){
        Usuario usuario = getUserByUsername(username);
        Usuario seguido = getUserById(seguidoId);

        if(!usuario.getSeguidos().contains(seguido)){
            throw new OperacionNoPermitidaExc("No sigue a este usuario");
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

    public UsuarioResponseDTO bloquearUsuario(String username, Long idBloqueado){

        Usuario usuario = getUserByUsername(username);
        Usuario bloqueado = getUserById(idBloqueado);

        if(usuario.getUsuariosBloqueados().contains(bloqueado)){
            throw new RuntimeException("El usuario ya está bloqueado");
        }

        usuario.getUsuariosBloqueados().add(bloqueado);

        usuarioRepo.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponseDTO desbloquearUsuario(String username, Long idBloqueado){

        Usuario usuario = getUserByUsername(username);
        Usuario bloqueado = getUserById(idBloqueado);

        if(!usuario.getUsuariosBloqueados().contains(bloqueado)){
            throw new RuntimeException("El usuario no está bloqueado");
        }

        usuario.getUsuariosBloqueados().remove(bloqueado);

        usuarioRepo.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public List<UsuarioResponseDTO> verUsuariosBloqueados(String username){

        Usuario usuario = getUserByUsername(username);

        return usuario.getUsuariosBloqueados().stream().map(usuarioMapper::toDTO).toList();
    }

    public List<NotificacionResponseDto> verNotificaciones(String username){
        Usuario usuario = usuarioRepo.findByUsername(username).orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado."));;

        return notificacionRepository.findByUsuarioIdUsuario(usuario.getIdUsuario()).stream()
                .map(notificacionMapper::toDto)
                .toList();
    }

    @Transactional
    public NotificacionResponseDto marcarNotificacionComoLeida(Long notificacionId, String username){
        Usuario usuario = usuarioRepo.findByUsername(username).orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado."));

        Notificacion notificacion = notificacionRepository.findById(notificacionId).orElseThrow(() -> new RecursoNoEncontradoExc("Notificación no encontrada"));


        if(!notificacion.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
            throw new OperacionNoPermitidaExc("No puedes modificar esta notificación");
        }

        notificacion.setLeida(true);

        return notificacionMapper.toDto(notificacionRepository.save(notificacion));
    }

    public boolean puedeVerPerfil(Usuario visitante, Usuario propietario){
        if(visitante.getIdUsuario().equals(propietario.getIdUsuario())){
            return true;
        }
        if(propietario.isPerfilPublico()){
            return true;
        }

        return propietario.getSeguidores().contains(visitante);
    }

}
