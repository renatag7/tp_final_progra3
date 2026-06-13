package tp_final_progra3.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.service.ReviewService;
import tp_final_progra3.demo.service.UsuarioService;

@RestController
@RequestMapping({"/juegos"})
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UsuarioService usuarioService;

    @PostMapping("/{juegoId}/reviews")
    public ResponseEntity<ReviewResponseDTO> createReview(@PathVariable Long juegoId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO, Authentication authentication){
        if(authentication == null){
            throw new OperacionNoPermitidaExc("Usuario no autenticado");
        }
        String username = authentication.getName();

        ReviewResponseDTO reviewResponseDTO = reviewService.createReview(username, juegoId, reviewRequestDTO);
        return ResponseEntity.ok(reviewResponseDTO);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReview(@PathVariable Long reviewId, @RequestBody UpdateReviewRequestDTO requestDTO, Authentication authentication){

        return ResponseEntity.ok(reviewService.updateReview(reviewId, authentication.getName(), requestDTO));
    }

    @DeleteMapping("{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, Authentication authentication){
        reviewService.deleteReview(reviewId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
