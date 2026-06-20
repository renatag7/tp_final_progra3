package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.ComentarioReviewMapper;
import tp_final_progra3.demo.mapper.ReviewMapper;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.request.ComentarioReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.ReviewRequestDTO;
import tp_final_progra3.demo.model.dto.request.UpdateReviewRequestDTO;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.*;
import tp_final_progra3.demo.repository.ComentarioReviewRepository;
import tp_final_progra3.demo.repository.LikeReviewRepository;
import tp_final_progra3.demo.repository.NotificacionRepository;
import tp_final_progra3.demo.repository.ReviewRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final JuegoApiService juegoService;
    private final UsuarioService usuarioService;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final LikeReviewRepository likeReviewRepository;
    private final UsuarioMapper usuarioMapper;
    private final ComentarioReviewRepository comentarioReviewRepository;
    private final ComentarioReviewMapper comentarioReviewMapper;
    private final NotificacionRepository notificacionRepository;

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

    @Transactional
    public void deleteReview(Long reviewId, String username){
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RecursoNoEncontradoExc("Review no encontrada"));

        if(!review.getUsuario().getUsername().equals(username)){
            throw new OperacionNoPermitidaExc("No puedes eliminar esta review");
        }

        reviewRepository.delete(review);
    }

    public List<ReviewResponseDTO> getReviewsByJuego(Long juegoId, String username){
        juegoService.getJuegoEntityById(juegoId);
        Usuario actual = usuarioService.getUserByUsername(username);

        return reviewRepository.findByJuegoId(juegoId).stream()
                .filter(review -> {Usuario autor = review.getUsuario();
                return !actual.getUsuariosBloqueados().contains(autor) && !autor.getUsuariosBloqueados().contains(actual);})
                .map(reviewMapper::toDto)
                .toList();
    }

    public List<ReviewResponseDTO> getReviewsByUsuario(Long usuarioId){
        if(usuarioService.getUserById(usuarioId) == null){
            throw new RecursoNoEncontradoExc("El usuario no existe");
        }

        return reviewRepository.findByUsuario_IdUsuario(usuarioId).stream()
                .map(reviewMapper::toDto)
                .toList();
    }

    @Transactional
    public void darLike(Long reviewId, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));

        Usuario autorReview = review.getUsuario();

        if(usuario.getUsuariosBloqueados().contains(autorReview) || autorReview.getUsuariosBloqueados().contains(usuario)) {
            throw new OperacionNoPermitidaExc("No puedes interactuar con este usuario");
        }

        if(review.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
            throw new OperacionNoPermitidaExc("No puedes darle like a tu propia review");
        }

        if(likeReviewRepository.findByUsuarioAndReview(usuario,review).isPresent()){
            throw new OperacionNoPermitidaExc("Ya diste like a esta review");
        }
        LikeReview like = new LikeReview();

        like.setUsuario(usuario);
        like.setReview(review);
        likeReviewRepository.save(like);

        review.setCantidadLikes(review.getCantidadLikes() + 1);
        reviewRepository.save(review);

        Notificacion notificacion = new Notificacion();

        notificacion.setUsuario(review.getUsuario());
        notificacion.setMensaje(usuario.getUsername() + " le dio like a tu reseña de " + review.getJuego().getTitulo());
        notificacion.setFecha(LocalDateTime.now());
        notificacion.setLeida(false);

        notificacionRepository.save(notificacion);
    }

    @Transactional
    public void quitarLike(Long reviewId, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));

        LikeReview like = likeReviewRepository.findByUsuarioAndReview(usuario, review).orElseThrow(()-> new OperacionNoPermitidaExc("No has dado like a esta review"));

        likeReviewRepository.delete(like);

        review.setCantidadLikes(review.getCantidadLikes() - 1);
        reviewRepository.save(review);
    }


    public List<UsuarioResponseDTO> getUsuariosLikes(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));

        return likeReviewRepository.findByReview(review).stream()
                .map(LikeReview::getUsuario)
                .map(usuarioMapper::toDTO)
                .toList();
    }

    @Transactional
    public ComentarioReviewResponseDTO comentarReview(Long reviewId, String username, ComentarioReviewRequestDTO requestDTO){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));

        Usuario autorReview = review.getUsuario();

        if(usuario.getUsuariosBloqueados().contains(autorReview) || autorReview.getUsuariosBloqueados().contains(usuario)) {
            throw new OperacionNoPermitidaExc("No puedes interactuar con este usuario");
        }

        ComentarioReview comentarioReview = new ComentarioReview();
        comentarioReview.setContenido(requestDTO.contenido());
        comentarioReview.setFechaPublicado(LocalDate.now());
        comentarioReview.setUsuario(usuario);
        comentarioReview.setReview(review);

        ComentarioReview guardado = comentarioReviewRepository.save(comentarioReview);

        if(!autorReview.getIdUsuario().equals(usuario.getIdUsuario())){
            Notificacion notificacion = new Notificacion();

            notificacion.setUsuario(autorReview);
            notificacion.setMensaje(usuario.getUsername() + " comentó tu reseña de " + review.getJuego().getTitulo());
            notificacion.setFecha(LocalDateTime.now());
            notificacion.setLeida(false);

            notificacionRepository.save(notificacion);
        }


        return comentarioReviewMapper.toDTO(guardado);
    }

    public List<ComentarioReviewResponseDTO> getComentariosByReviews(Long reviewId, String username){
        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new RecursoNoEncontradoExc("Review no encontrada"));
        Usuario actual = usuarioService.getUserByUsername(username);

        return comentarioReviewRepository.findByReview(review).stream()
                .filter(comentario -> {Usuario autor = comentario.getUsuario();
                    return !actual.getUsuariosBloqueados().contains(autor) && !autor.getUsuariosBloqueados().contains(actual);})
                .map(comentarioReviewMapper::toDTO)
                .toList();
    }

    @Transactional
    public void deleteComentario(Long comentarioId, String username){
        Usuario usuario = usuarioService.getUserByUsername(username);
        ComentarioReview comentarioReview = comentarioReviewRepository.findById(comentarioId).orElseThrow(()-> new RecursoNoEncontradoExc("Comentario no encontrado"));

        if(!comentarioReview.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
            throw new OperacionNoPermitidaExc("No es posible eliminar comentarios de otros usuarios");
        }

        comentarioReviewRepository.delete(comentarioReview);
    }
}
