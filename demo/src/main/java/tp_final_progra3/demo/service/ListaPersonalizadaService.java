package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.ListaPersonalizadaMapper;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.ListaPersonalizada;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.JuegoRepository;
import tp_final_progra3.demo.repository.ListaPersonalizadaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListaPersonalizadaService {
    private final ListaPersonalizadaRepository listaPersonalizadaRepository;
    private final UsuarioService usuarioService;
    private final ListaPersonalizadaMapper listaPersonalizadaMapper;
    private final JuegoRepository juegoRepository;


    public ListaPersonalizadaResponseDTO crearLista (ListaPersonalizadaRequestDTO requestDTO, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);
        ListaPersonalizada listaPersonalizada = listaPersonalizadaMapper.ToEntity(requestDTO);

        listaPersonalizada.setUsuario(usuario);
        listaPersonalizada.setFechaCreacion(LocalDateTime.now());
        ListaPersonalizada listaPersonalizada1 = listaPersonalizadaRepository.save(listaPersonalizada);

        return listaPersonalizadaMapper.ToDto(listaPersonalizada1);


    }
    public ListaPersonalizadaResponseDTO agregarJuegoALista(Long idLista, Long idJuego, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);

        ListaPersonalizada listaPersonalizada = listaPersonalizadaRepository.findById(idLista).orElseThrow(()-> new RecursoNoEncontradoExc("lista no encontrada") );
        if(!listaPersonalizada.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
            throw new OperacionNoPermitidaExc("No puedes modificar esta lista.");
        }
        Juego juego = juegoRepository.findByIdAndActivoTrue(idJuego).orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));

        listaPersonalizada.getJuegos().add(juego);
        ListaPersonalizada listaPersonalizadaGuardada = listaPersonalizadaRepository.save(listaPersonalizada);
        return listaPersonalizadaMapper.ToDto(listaPersonalizadaGuardada);
    }


    public ListaPersonalizadaResponseDTO eliminarJuegoDeLista (Long idLista, Long idJuego, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);

        ListaPersonalizada listaPersonalizada = listaPersonalizadaRepository.findById(idLista).orElseThrow(()-> new RecursoNoEncontradoExc("Lista no encontrada") );
        if(!listaPersonalizada.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
            throw new OperacionNoPermitidaExc("No puedes modificar esta lista.");
        }

        Juego juego = juegoRepository.findByIdAndActivoTrue(idJuego).orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));

        listaPersonalizada.getJuegos().remove(juego);
        ListaPersonalizada listaPersonalizadaGuardada = listaPersonalizadaRepository.save(listaPersonalizada);
        return listaPersonalizadaMapper.ToDto(listaPersonalizadaGuardada);
    }


    public List<ListaPersonalizadaResponseDTO> verListasDeOtroUsuario(Long idUsuario, String username){
        Usuario actual = usuarioService.getUserByUsername(username);
        Usuario duenioLista = usuarioService.getUserById(idUsuario);
        if(actual.getUsuariosBloqueados().contains(duenioLista) || duenioLista.getUsuariosBloqueados().contains(actual)) {
            throw new OperacionNoPermitidaExc("No puedes ver las listas de este usuario");
        }

        if(!usuarioService.puedeVerPerfil(actual, duenioLista)){
            throw new OperacionNoPermitidaExc("No puedes ver las listas de este usuario porque su perfil es privado");
        }

        List <ListaPersonalizada> listas= listaPersonalizadaRepository.findByUsuario_IdUsuarioAndEsPublicaTrue(idUsuario);

        return listas.stream().map(listaPersonalizadaMapper::ToDto).toList();
    }

    public List<ListaPersonalizadaResponseDTO> verMisListas(String username){
        Usuario usuario = usuarioService.getUserByUsername(username);

        List<ListaPersonalizada> listas = listaPersonalizadaRepository.findByUsuarioOrderByFechaCreacionDesc(usuario);
        return listas.stream().map(listaPersonalizadaMapper::ToDto).toList();
    }


}
