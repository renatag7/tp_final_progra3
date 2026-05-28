package tp_final_progra3.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JuegoService {
    private final RestTemplate restTemplate = new RestTemplate();
}
