package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.model.dto.api.ApiResponseDTO;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.response.CompraResponseDto;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.entity.Genero;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Plataforma;
import tp_final_progra3.demo.repository.GeneroRepository;
import tp_final_progra3.demo.repository.JuegoRepository;
import tp_final_progra3.demo.repository.PlataformaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JuegoApiService {
    private final RestTemplate restTemplate;
    private final JuegoMapper juegoMapper;
    private final JuegoRepository juegoRepository;
    private final PlataformaRepository plataformaRepository;
    private final GeneroRepository generoRepository;

    @Value("${rawg.api.key}")
    private String apiKey;


    public List<JuegoResponseDTO> findAllGames(String nombre){
        String url = "https://api.rawg.io/api/games?search=" + nombre + "&key=" + apiKey;

        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(url, ApiResponseDTO.class);

        return apiResponseDTO.results().stream()
                .map(juegoMapper::fromApi)
                .map(juegoMapper::toDTO)
                .toList();
    }

    /*
    public List<JuegoResponseDTO> filtrarJuegosPorNombre(String nombre){

        String url = "https://api.rawg.io/api/games?search=" + nombre + "&key=" + apiKey;

        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(url, ApiResponseDTO.class);

        return apiResponseDTO.results().stream().map(juegoMapper::fromApi).map(juegoMapper::toDTO).toList();
    }

    public JuegoResponseDTO getJuegoFromApi(String nombre){
        String url = "https://api.rawg.io/api/games?search=" + nombre + "&key=" + apiKey;
        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(url, ApiResponseDTO.class);

        JuegoApiResponseDTO juegoApiResponseDTO = apiResponseDTO.results().getFirst();
        Juego juego = juegoMapper.fromApi(juegoApiResponseDTO);

        juegoRepository.save(juego);

        return juegoMapper.toDTO(juego);
    }*/

    public JuegoResponseDTO getJuegoById(Long id){
        Optional<Juego> juegoExistente = juegoRepository.findById(id);
        if(juegoExistente.isPresent()){
            if(!juegoExistente.get().getActivo()){
                throw new RecursoNoEncontradoExc("Juego no encontrado");
            }
            return juegoMapper.toDTO(juegoExistente.get());
        }

        String url = "https://api.rawg.io/api/games/" + id + "?key=" + apiKey;

        JuegoApiResponseDTO juegoApiResponseDTO = restTemplate.getForObject(url, JuegoApiResponseDTO.class);
        Juego juego = juegoMapper.fromApi(juegoApiResponseDTO);

        Set<Plataforma> plataformasFinales = new HashSet<>();

        for (Plataforma p : juego.getPlataformas()) {

            Plataforma existente = plataformaRepository
                    .findByNombre(p.getNombre())
                    .orElseGet(() -> plataformaRepository.save(p));

            plataformasFinales.add(existente);
        }

        juego.setPlataformas(plataformasFinales);

        Set<Genero> generosFinales = new HashSet<>();

        for (Genero g : juego.getGeneros()) {

            Genero existente = generoRepository
                    .findByNombre(g.getNombre())
                    .orElseGet(() -> generoRepository.save(g));

            generosFinales.add(existente);
        }

        juego.setGeneros(generosFinales);

        if (!juegoRepository.existsById(juego.getId())) {
            juegoRepository.save(juego);
        }

        return juegoMapper.toDTO(juego);
    }

    public List<JuegoResponseDTO> filtrarJuegosGenero(String genero){
        String urlFiltrar =
                "https://api.rawg.io/api/games?key=" + apiKey;

        if(genero != null && !genero.isBlank()){
            urlFiltrar += "&genres=" + genero;
        }

        ApiResponseDTO apiResponseDTO = restTemplate.getForObject(urlFiltrar, ApiResponseDTO.class);

        return apiResponseDTO.results().stream().map(juegoMapper::fromApi).map(juegoMapper::toDTO).toList();
    }

    public Juego getJuegoEntityById(Long id){

        return juegoRepository.findByIdAndActivoTrue(id).orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));
    }

    public CompraResponseDto enlaceCompra(Long idJuego){

        String url = "https://rawg.io/games/" + idJuego;

        return new CompraResponseDto(url);
    }
}
