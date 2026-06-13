package tp_final_progra3.demo.exceptions.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tp_final_progra3.demo.exceptions.general.RecursoDuplicadoExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final RolRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtService jwtService;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if(userRepository.existsByEmail(request.email())){
            throw new RecursoDuplicadoExc("El email ingresado ya se encuentra registrado.");
        }

        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RecursoDuplicadoExc("El nombre de usuario ingresado ya se encuentra registrado.");
        }

        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setFechaRegistro(LocalDate.now());
        usuario.setActivo(true);
        usuario.setPassword(passwordEncoder.encode(request.password()));

        usuario.setEnabled(true);
        usuario.setAccountNoExpired(true);
        usuario.setAccountNoLocked(true);
        usuario.setCredentialNoExpired(true);

        RolEntity userRole = roleRepository.findByRol(Rol.USER)
                .orElseThrow(() -> new RecursoNoEncontradoExc("No existe el rol USER."));

        usuario.setRoles(Set.of(userRole));

        Usuario usuarioGuardado = userRepository.save(usuario);
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuarioGuardado.getUsername());

        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDTO("Bearer", jwt);
    }
}
