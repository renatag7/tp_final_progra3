package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.ComentarioReviewMapper;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.mapper.ReviewMapper;
import tp_final_progra3.demo.mapper.UsuarioMapper;
import tp_final_progra3.demo.model.dto.response.ComentarioReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.ReviewResponseDTO;
import tp_final_progra3.demo.model.dto.response.UsuarioResponseDTO;
import tp_final_progra3.demo.model.entity.ComentarioReview;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Review;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.repository.ComentarioReviewRepository;
import tp_final_progra3.demo.repository.JuegoRepository;
import tp_final_progra3.demo.repository.ReviewRepository;
import tp_final_progra3.demo.repository.UsuarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UsuarioRepository usuarioRepository;
    private final ReviewRepository reviewRepository;
    private final JuegoRepository juegoRepository;
    private final UsuarioMapper usuarioMapper;
    private final JuegoMapper juegoMapper;
    private final ReviewMapper reviewMapper;
    private final JuegoApiService juegoApiService;
    private final ComentarioReviewRepository comentarioReviewRepository;
    private final ComentarioReviewMapper comentarioReviewMapper;


    public UsuarioResponseDTO bloquearUsuario(Long id){

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado"));

        usuario.setEnabled(false);

        usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    public List<UsuarioResponseDTO> verUsuariosBloqueados(){

        return usuarioRepository.findByIsEnabledFalse()
                .stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public void eliminarReview(Long id){

        Review review = reviewRepository.findById(id).orElseThrow(() ->
                        new RecursoNoEncontradoExc("Review no encontrada"));

        reviewRepository.delete(review);
    }

    public UsuarioResponseDTO rehabilitarUsuario(Long id){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoExc("Usuario no encontrado"));

        usuario.setEnabled(true);

        usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);
    }


    public JuegoResponseDTO deshabilitarJuego(Long id) {

        Juego juego = juegoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoExc("Juego no encontrado"));

        juego.setActivo(false);

        juegoRepository.save(juego);

        return juegoMapper.toDTO(juego);
    }

    public JuegoResponseDTO rehabilitarJuego(Long id) {

        Juego juego = juegoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoExc("Juego no encontrado"));

        juego.setActivo(true);

        juegoRepository.save(juego);

        return juegoMapper.toDTO(juego);

    }

    public List<UsuarioResponseDTO> verTodosLosUsuarios(){
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDTO).toList();

    }

    public List<UsuarioResponseDTO> filtrarPorNombreUsuario(String username){
        return usuarioRepository.findByUsernameContainingIgnoreCase(username).stream().map(usuarioMapper::toDTO).toList();
    }

    public List <ReviewResponseDTO> verTodasLasReviews(){
        return reviewRepository.findAll().stream().map(reviewMapper::toDto).toList();
    }

    @Transactional
    public void eliminarComentario(Long comentarioId){
        ComentarioReview comentario = comentarioReviewRepository.findById(comentarioId).orElseThrow(() -> new RecursoNoEncontradoExc("Comentario no encontrado"));

        comentarioReviewRepository.delete(comentario);
    }

    public List<ComentarioReviewResponseDTO> verTodosLosComentarios(){

        return comentarioReviewRepository.findAll().stream()
                .map(comentarioReviewMapper::toDTO)
                .toList();
    }

    public JuegoResponseDTO getJuegoByIdAdmin(Long id){
        Juego juego = juegoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoExc("Juego no encontrado"));

        return juegoMapper.toDTO(juego);
    }

}







