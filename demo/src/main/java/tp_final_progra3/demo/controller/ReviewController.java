package tp_final_progra3.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.model.dto.request.ComentarioReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.service.ReviewService;
import tp_final_progra3.demo.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping({""})
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/juegos/{juegoId}/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(@PathVariable Long juegoId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO, Authentication authentication){
        if(authentication == null){
            throw new OperacionNoPermitidaExc("Usuario no autenticado");
        }
        String username = authentication.getName();

        ReviewResponseDTO reviewResponseDTO = reviewService.createReview(username, juegoId, reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewRequestDTO requestDTO, Authentication authentication){

        return ResponseEntity.ok(reviewService.updateReview(reviewId, authentication.getName(), requestDTO));
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, Authentication authentication){
        reviewService.deleteReview(reviewId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reviews/{reviewId}/like")
    public ResponseEntity<ReviewResponseDTO> darLike(@PathVariable Long reviewId, Authentication authentication){
        return ResponseEntity.ok(reviewService.darLike(reviewId, authentication.getName()));
    }

    @DeleteMapping("/reviews/{reviewId}/like")
    public ResponseEntity<Void> quitarLike(@PathVariable Long reviewId, Authentication authentication){
        reviewService.quitarLike(reviewId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews/{reviewId}/likes")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuariosQueDieronLike(@PathVariable Long reviewId){

        return ResponseEntity.ok(reviewService.getUsuariosLikes(reviewId));
    }

    @PostMapping("/reviews/{reviewId}/comentarios")
    public ResponseEntity<ComentarioReviewResponseDTO> createComentario(@PathVariable Long reviewId, @Valid @RequestBody ComentarioReviewRequestDTO requestDTO, Authentication authentication){
        if(authentication == null){
            throw new OperacionNoPermitidaExc("Usuario no autenticado");
        }
        String username = authentication.getName();

        ComentarioReviewResponseDTO comentarioReviewResponseDTO = reviewService.comentarReview(reviewId, username, requestDTO);
        return ResponseEntity.ok(comentarioReviewResponseDTO);
    }

    @GetMapping("/reviews/{reviewId}/comentarios")
    public ResponseEntity<List<ComentarioReviewResponseDTO>> getComentariosByReview(@PathVariable Long reviewId, Authentication authentication){
        return ResponseEntity.ok(reviewService.getComentariosByReviews(reviewId, authentication.getName()));
    }

    @DeleteMapping("/comentarios/{comentarioId}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long comentarioId, Authentication authentication){
        reviewService.deleteComentario(comentarioId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
