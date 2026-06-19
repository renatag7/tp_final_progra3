package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.ComentarioReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.entity.ComentarioReview;
import tp_final_progra3.demo.model.entity.Usuario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T21:45:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class ComentarioReviewMapperImpl implements ComentarioReviewMapper {

    @Override
    public ComentarioReview toEntity(ComentarioReviewRequestDTO comentarioReviewRequestDTO) {
        if ( comentarioReviewRequestDTO == null ) {
            return null;
        }

        ComentarioReview comentarioReview = new ComentarioReview();

        comentarioReview.setContenido( comentarioReviewRequestDTO.contenido() );

        return comentarioReview;
    }

    @Override
    public ComentarioReviewResponseDTO toDTO(ComentarioReview comentarioReview) {
        if ( comentarioReview == null ) {
            return null;
        }

        String username = null;
        Long id = null;
        String contenido = null;
        LocalDate fechaPublicado = null;

        username = comentarioReviewUsuarioUsername( comentarioReview );
        id = comentarioReview.getId();
        contenido = comentarioReview.getContenido();
        fechaPublicado = comentarioReview.getFechaPublicado();

        ComentarioReviewResponseDTO comentarioReviewResponseDTO = new ComentarioReviewResponseDTO( id, contenido, username, fechaPublicado );

        return comentarioReviewResponseDTO;
    }

    private String comentarioReviewUsuarioUsername(ComentarioReview comentarioReview) {
        Usuario usuario = comentarioReview.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getUsername();
    }
}
