package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.repository.JuegoRepository;

@Service
@RequiredArgsConstructor
public class JuegoService {
    private final JuegoMapper juegoMapper;
    private final JuegoRepository juegoRepository;

}

