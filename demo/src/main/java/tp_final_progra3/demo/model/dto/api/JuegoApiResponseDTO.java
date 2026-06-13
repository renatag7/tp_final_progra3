package tp_final_progra3.demo.model.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public record JuegoApiResponseDTO(
        Long id,
        String name,
        @JsonProperty("description_raw")
        String description,
        String released,
        Double rating,
        Set<GeneroApiDTO> genres,
        Set<PlataformaWrapperDTO> platforms,
        Set<DeveloperApiDTO> developers
) {}