package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.mapper.ListaPersonalizadaMapper;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.model.entity.ListaPersonalizada;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.ListaPersonalizadaRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ListaPersonalizadaService {
    private final ListaPersonalizadaRepository listaPersonalizadaRepository;
    private final UsuarioService usuarioService;
    private final ListaPersonalizadaMapper listaPersonalizadaMapper;

    public ListaPersonalizadaResponseDTO crearLista (ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO){
        Usuario usuario = usuarioService.getUserById(listaPersonalizadaRequestDTO.usuario());
        ListaPersonalizada listaPersonalizada = listaPersonalizadaMapper.ToEntity(listaPersonalizadaRequestDTO);

        listaPersonalizada.setUsuario(usuario);
        listaPersonalizada.setFecha_creacion(LocalDateTime.now());
        ListaPersonalizada listaPersonalizada1 = listaPersonalizadaRepository.save(listaPersonalizada);

        return listaPersonalizadaMapper.ToDto(listaPersonalizada1);


    }







}
