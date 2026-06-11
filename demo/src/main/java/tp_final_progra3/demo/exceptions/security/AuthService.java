package tp_final_progra3.demo.exceptions.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Rol;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final RolRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailServiceImpl userDetailsService;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con ese username");
        }

        RolEntity userRole = roleRepository.findByRoleEnum(Rol.USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No existe el rol USER inicial"));

        Usuario newUser = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialNoExpired(true)
                .roles(Set.of(userRole))
                .build();

        userRepository.save(newUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getUsername());
        String jwt = jwtService.generateToken(userDetails);

        return new AuthResponseDTO("Bearer", jwt);
    }
}
