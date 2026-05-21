package tp_final_progra3.demo.model.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record JuegoApiResponseDTO(

        Long id,

        String name,

        @JsonProperty("description_raw")
        String description,

        String released,

        Double rating,

        List<GeneroApiDTO> genres,

        List<PlataformaApiDTO> platforms,

        List<DesarrolladoraApiDTO> developers

) {}