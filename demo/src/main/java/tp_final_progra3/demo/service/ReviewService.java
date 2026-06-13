package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.ReviewMapper;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Review;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final JuegoApiService juegoService;
    private final UsuarioService usuarioService;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewResponseDTO createReview(String username, Long juegoId, ReviewRequestDTO reviewRequestDTO){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Juego juego = juegoService.getJuegoEntityById(juegoId);

        Review review = new Review();
        review.setContenido(reviewRequestDTO.contenido());
        review.setPuntuacion(reviewRequestDTO.puntuacion());
        review.setContieneSpoilers(reviewRequestDTO.contieneSpoilers());
        review.setUsuario(usuario);
        review.setJuego(juego);
        review.setFechaPublicacion(LocalDate.now());

        Review guardada = reviewRepository.save(review);
        return reviewMapper.toDto(guardada);
    }

    public ReviewResponseDTO updateReview(Long reviewId, String username, UpdateReviewRequestDTO requestDTO){
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));

        if(!review.getUsuario().getUsername().equals(username)){
            throw new OperacionNoPermitidaExc("No puedes modificar esta review.");
        }

        if(requestDTO.contenido() != null){
            review.setContenido(requestDTO.contenido());
        }

        if(requestDTO.puntuacion() != null){
            review.setPuntuacion(requestDTO.puntuacion());
        }

        if(requestDTO.contieneSpoilers() != null){
            review.setContieneSpoilers(requestDTO.contieneSpoilers());
        }

        reviewRepository.save(review);
        return reviewMapper.toDto(review);
    }

    public void deleteReview(Long reviewId, String username){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RecursoNoEncontradoExc("Review no encontrada"));

        if(!review.getUsuario().getUsername().equals(username)){
            throw new OperacionNoPermitidaExc("No puedes eliminar esta review");
        }

        reviewRepository.delete(review);
    }

    public List<ReviewResponseDTO> getReviewsByJuego(Long juegoId){

        return reviewRepository.findByJuegoId(juegoId).stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    public List<ReviewResponseDTO> getReviewsByUsuario(Long usuarioId){
        if(usuarioService.getUserById(usuarioId) == null){
            throw new RecursoNoEncontradoExc("El usuario no existe");
        }

        return reviewRepository.findByUsuarioId(usuarioId).stream()
                .map(reviewMapper::toDto)
                .toList();
    }
}
