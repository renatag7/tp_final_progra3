package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.entity.Review;

@Mapper(componentModel = "spring ")
public interface ReviewMapper {
    @Mapping(target = "username", source = "usuario.username")
    @Mapping(target = "tituloJuego", source = "juego.titulo")
    @Mapping(target = "id", source = "id_review")
    ReviewResponseDTO toDto(Review review);

    @Mapping(target = "id_review", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "juego", ignore = true)
    @Mapping(target = "fechaPublicacion", ignore = true)
    Review toEntity(ReviewRequestDTO reviewRequestDTO);


}
