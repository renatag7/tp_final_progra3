package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.JuegoNoExisteExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.EstadoJuegoMapper;
import tp_final_progra3.demo.model.dto.request.EstadoJuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.EstadoJuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.HistorialJuegoResponseDTO;
import tp_final_progra3.demo.model.entity.EstadoJuegoUsuario;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.EstadoJuegoUsuarioRepository;
import tp_final_progra3.demo.repository.JuegoRepository;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoJuegoUsuarioService {

    private final EstadoJuegoUsuarioRepository estadoJuegoUsuarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final JuegoRepository juegoRepository;
    private final EstadoJuegoMapper estadoJuegoMapper;
    private final UsuarioService usuarioService;

    public EstadoJuegoResponseDTO crearEstado (Long id, EstadoJuegoRequestDTO estadoJuegoRequestDTO){
        Usuario usuario = usuarioRepository.findById(id ).orElseThrow(()-> new RecursoNoEncontradoExc("usuario no encontrado"));
        Juego juego = juegoRepository.findById(id). orElseThrow(()-> new JuegoNoExisteExc("juego no encontrado"));

        EstadoJuegoUsuario estadoJuegoUsuario = estadoJuegoMapper.ToEntity(estadoJuegoRequestDTO);

        estadoJuegoUsuario.setUsuario(usuario);
        estadoJuegoUsuario.setJuego(juego);
        estadoJuegoUsuario.setFecha_actualizacion(LocalDate.now());

        EstadoJuegoUsuario estadoJuegoUsuarioGuardado = estadoJuegoUsuarioRepository.save(estadoJuegoUsuario);

        return estadoJuegoMapper.ToDto(estadoJuegoUsuarioGuardado);

    }

    public List<HistorialJuegoResponseDTO> getHistorialUsuario(String username){
        Usuario usuario = usuarioService.getUserByUsername(username);

        return estadoJuegoUsuarioRepository.findByUserOrderByFecha(usuario).stream()
                .map(estado -> new HistorialJuegoResponseDTO(
                        estado.getJuego().getTitulo(),
                        estado.getEstado(),
                        estado.getFecha_actualizacion()
                ))
                .toList();
    }


}
