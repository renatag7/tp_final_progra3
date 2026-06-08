package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_final_progra3.demo.exceptions.JuegoNoExisteExc;
import tp_final_progra3.demo.exceptions.UsuarioNoExisteExc;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.JuegoRepository;

@Service
@RequiredArgsConstructor
public class JuegoService {
    private final JuegoMapper juegoMapper;
    private final JuegoRepository juegoRepository;

}
