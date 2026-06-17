package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.EstadoJuegoMapper;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.enums.Estado;
import tp_final_progra3.demo.repository.EstadoJuegoUsuarioRepository;
import tp_final_progra3.demo.repository.JuegoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoJuegoUsuarioService {

    private final EstadoJuegoUsuarioRepository estadoJuegoUsuarioRepository;
    private final JuegoRepository juegoRepository;
    private final EstadoJuegoMapper estadoJuegoMapper;
    private final UsuarioService usuarioService;

    public EstadoJuegoResponseDTO actualizarEstado (Long juegoId, EstadoJuegoRequestDTO estadoJuegoRequestDTO, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Juego juego = juegoRepository.findById(juegoId). orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));

        Optional<EstadoJuegoUsuario> existente = estadoJuegoUsuarioRepository.findByUserAndJuego(usuario, juego);
        EstadoJuegoUsuario estadoJuegoUsuario;

        if(existente.isPresent()){
            estadoJuegoUsuario = existente.get();
            estadoJuegoUsuario.setEstado(estadoJuegoRequestDTO.estado());
            estadoJuegoUsuario.setFecha_actualizacion(LocalDate.now());
        }else{
            estadoJuegoUsuario = estadoJuegoMapper.ToEntity(estadoJuegoRequestDTO);
            estadoJuegoUsuario.setUsuario(usuario);
            estadoJuegoUsuario.setJuego(juego);
            estadoJuegoUsuario.setFecha_actualizacion(LocalDate.now());
        }

        EstadoJuegoUsuario guardado = estadoJuegoUsuarioRepository.save(estadoJuegoUsuario);

        return estadoJuegoMapper.ToDto(guardado);

    }

    public List<EstadoJuegoResponseDTO> getHistorialUsuario(String username, Estado estado){
        Usuario usuario = usuarioService.getUserByUsername(username);

        List<EstadoJuegoUsuario> historial = new ArrayList<>();
        if(estado == null){
            historial = estadoJuegoUsuarioRepository.findByUserOrderByFecha(usuario);
        }else{
            historial = estadoJuegoUsuarioRepository.findByUserAndEstadoOrderByFecha(usuario, estado);
        }

        return historial.stream()
                .map(e -> new EstadoJuegoResponseDTO(
                        e.getJuego().getTitulo(),
                        e.getEstado(),
                        e.getFecha_actualizacion()
                ))
                .toList();
    }


}
