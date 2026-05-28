package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.api.GeneroApiDTO;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.api.PlataformaApiDTO;
import tp_final_progra3.demo.model.dto.request.JuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.entity.Genero;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Plataforma;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface JuegoMapper {
    JuegoResponseDTO toDTO(Juego juego);
    Juego toEntity(JuegoRequestDTO juegoRequestDTO);


    @Mapping(target = "titulo", source = "name")
    @Mapping(target = "sinopsis", source = "description")
    @Mapping(target = "fecha_lanzamiento", expression = "java(juegoApiResponseDTO.released() == null ? null : java.time.LocalDate.parse(juegoApiResponseDTO.released()))")
    @Mapping(target = "rating_general", source = "rating")
    @Mapping(target = "generos", source = "genres")
    @Mapping(target = "plataformas", source = "platforms")
    @Mapping(target = "desarrolladora", expression = "java(juegoApiResponseDTO.developers().isEmpty() ? null : juegoApiResponseDTO.developers().getFirst().name())")
    Juego fromApi(JuegoApiResponseDTO juegoApiResponseDTO);


    default Set<Genero> mapGeneros(List<GeneroApiDTO> generoApiDTOS){

        return generoApiDTOS.stream()
                .map(this::toGenero)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "nombre", source = "name")
    Genero toGenero(GeneroApiDTO generoApiDTO);

    default Set<Plataforma> mapPlataformas(List<PlataformaApiDTO> plataformaApiDTOS){

        return plataformaApiDTOS.stream()
                .map(this::toPlataforma)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "nombre", source = "name")
    Plataforma toPlataforma(PlataformaApiDTO plataformaApiDTO);
}
