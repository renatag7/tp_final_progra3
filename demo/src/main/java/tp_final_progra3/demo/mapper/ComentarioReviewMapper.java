package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.request.ComentarioReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.RegisterRequestDTO;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.ComentarioReview;
import tp_final_progra3.demo.model.entity.Usuario;

@Mapper(componentModel = "spring")
public interface ComentarioReviewMapper {
    ComentarioReview toEntity(ComentarioReviewRequestDTO comentarioReviewRequestDTO);

    @Mapping(target = "username", source = "usuario.username")
    ComentarioReviewResponseDTO toDTO(ComentarioReview comentarioReview);
}
