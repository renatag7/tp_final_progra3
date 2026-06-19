package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Review;
import tp_final_progra3.demo.model.entity.Usuario;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T21:45:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewResponseDTO toDto(Review review) {
        if ( review == null ) {
            return null;
        }

        String username = null;
        String tituloJuego = null;
        Long id = null;
        String contenido = null;
        Float puntuacion = null;
        LocalDate fechaPublicacion = null;
        Boolean contieneSpoilers = null;
        Integer cantidadLikes = null;

        username = reviewUsuarioUsername( review );
        tituloJuego = reviewJuegoTitulo( review );
        id = review.getId_review();
        contenido = review.getContenido();
        puntuacion = review.getPuntuacion();
        fechaPublicacion = review.getFechaPublicacion();
        contieneSpoilers = review.isContieneSpoilers();
        cantidadLikes = review.getCantidadLikes();

        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO( id, username, tituloJuego, contenido, puntuacion, fechaPublicacion, contieneSpoilers, cantidadLikes );

        return reviewResponseDTO;
    }

    @Override
    public Review toEntity(ReviewRequestDTO reviewRequestDTO) {
        if ( reviewRequestDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setContenido( reviewRequestDTO.contenido() );
        review.setPuntuacion( reviewRequestDTO.puntuacion() );
        if ( reviewRequestDTO.contieneSpoilers() != null ) {
            review.setContieneSpoilers( reviewRequestDTO.contieneSpoilers() );
        }

        return review;
    }

    private String reviewUsuarioUsername(Review review) {
        Usuario usuario = review.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getUsername();
    }

    private String reviewJuegoTitulo(Review review) {
        Juego juego = review.getJuego();
        if ( juego == null ) {
            return null;
        }
        return juego.getTitulo();
    }
}
