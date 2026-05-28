package tp_final_progra3.demo.model.dto.api;

import java.util.List;

public record ApiResponseDTO(
        List<JuegoApiResponseDTO> results
) {
}
