package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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


    public ListaPersonalizadaResponseDTO crearLista (ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO){
        Usuario usuario = usuarioService.getUserById(listaPersonalizadaRequestDTO.usuario());
        ListaPersonalizada listaPersonalizada = listaPersonalizadaMapper.ToEntity(listaPersonalizadaRequestDTO);

        listaPersonalizada.setUsuario(usuario);
        listaPersonalizada.setFecha_creacion(LocalDateTime.now());
        ListaPersonalizada listaPersonalizada1 = listaPersonalizadaRepository.save(listaPersonalizada);

        return listaPersonalizadaMapper.ToDto(listaPersonalizada1);


    }
    public ListaPersonalizadaResponseDTO agregarJuegoaLista (Long idLista, Long idJuego){
        ListaPersonalizada listaPersonalizada = listaPersonalizadaRepository.findById(idLista).orElseThrow(()-> new RuntimeException("lista no encontrada") );
        Juego juego = juegoRepository.findById(idJuego).orElseThrow(()-> new RuntimeException("juego no encontrado "));

        listaPersonalizada.getJuegos().add(juego);
        ListaPersonalizada listaPersonalizadaGuardada = listaPersonalizadaRepository.save(listaPersonalizada);
        return listaPersonalizadaMapper.ToDto(listaPersonalizadaGuardada);
    }


    public ListaPersonalizadaResponseDTO eliminarJuegoDeLista (Long idLista, Long idJuego){
        ListaPersonalizada listaPersonalizada = listaPersonalizadaRepository.findById(idLista).orElseThrow(()-> new RuntimeException("lista no encontrada") );
        Juego juego = juegoRepository.findById(idJuego).orElseThrow(()-> new RuntimeException("juego no encontrado "));

        listaPersonalizada.getJuegos().remove(juego);
        ListaPersonalizada listaPersonalizadaGuardada = listaPersonalizadaRepository.save(listaPersonalizada);
        return listaPersonalizadaMapper.ToDto(listaPersonalizadaGuardada);
    }


    public List<ListaPersonalizadaResponseDTO> verLIstaDeOtroUsuario (Long idUsuario){
        List < ListaPersonalizada> listas= listaPersonalizadaRepository.findByUsuarioAndEsPublica(idUsuario);
        return listas.stream().map(listaPersonalizadaMapper::ToDto).toList();
    }





}
