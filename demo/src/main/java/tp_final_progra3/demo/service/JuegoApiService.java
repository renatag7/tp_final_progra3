package tp_final_progra3.demo.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.model.dto.api.ApiResponseDTO;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.repository.JuegoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegoApiService {
    private final RestTemplate restTemplate;
    private final JuegoMapper juegoMapper;
    private final JuegoRepository juegoRepository;



    @Value("${rawg.api.key}")
    private String apiKey;


    public List<JuegoResponseDTO> findAllGames(String nombre){
        String url = "https://api.rawg.io/api/games?search=" + nombre + "&key=" + apiKey;

        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(url, ApiResponseDTO.class);

        System.out.println("ENTRÓ AL SERVICE");

        apiResponseDTO.results().forEach(dto -> {

            Juego juego = juegoMapper.fromApi(dto);

            System.out.println("JUEGO: " + juego.getTitulo());

            juego.getPlataformas()
                    .forEach(p -> System.out.println("PLATAFORMA: " + p.getNombre()));

            System.out.println("DEVELOPER: " + juego.getDeveloper());
        });

        return apiResponseDTO.results().stream()
                .map(juegoMapper::fromApi)
                .map(juegoMapper::toDTO)
                .toList();
    }

    public JuegoResponseDTO getJuegoFromApi(String nombre){
        String url = "https://api.rawg.io/api/games?search=" + nombre + "&key=" + apiKey;
        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(url, ApiResponseDTO.class);

        JuegoApiResponseDTO juegoApiResponseDTO = apiResponseDTO.results().getFirst();
        Juego juego = juegoMapper.fromApi(juegoApiResponseDTO);

        juegoRepository.save(juego);

        return juegoMapper.toDTO(juego);
    }
}
