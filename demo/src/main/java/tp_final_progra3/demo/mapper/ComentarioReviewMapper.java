package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import tp_final_progra3.demo.model.dto.request.ComentarioReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.ComentarioReview;
import tp_final_progra3.demo.model.entity.Usuario;

@Mapper(componentModel = "spring")
public interface ComentarioReviewMapper {
    ComentarioReview toEntity(ComentarioReviewRequestDTO comentarioReviewRequestDTO);
    ComentarioReviewResponseDTO toDTO(ComentarioReview comentarioReview);
}
