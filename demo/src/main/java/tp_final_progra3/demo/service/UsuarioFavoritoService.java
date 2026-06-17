package tp_final_progra3.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp_final_progra3.demo.exceptions.general.OperacionNoPermitidaExc;
import tp_final_progra3.demo.exceptions.general.RecursoNoEncontradoExc;
import tp_final_progra3.demo.mapper.JuegoMapper;
import tp_final_progra3.demo.model.dto.response.FavoritoResponseDTO;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Usuario;
import tp_final_progra3.demo.model.entity.UsuarioFavorito;
import tp_final_progra3.demo.repository.JuegoRepository;
import tp_final_progra3.demo.repository.UsuarioFavoritoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioFavoritoService {
    private final UsuarioFavoritoRepository usuarioFavoritoRepository;
    private final UsuarioService usuarioService;
    private final JuegoRepository juegoRepository;
    private final JuegoMapper juegoMapper;

    public List<FavoritoResponseDTO> agregarFavorito(String username, Long juegoId, Integer posicion){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Juego juego = juegoRepository.findById(juegoId).orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));

        if(usuarioFavoritoRepository.findByUsuarioAndJuego(usuario, juego).isPresent()){
            throw new OperacionNoPermitidaExc("El juego ya se encuentra en favoritos");
        }

        if(usuarioFavoritoRepository.countByUsuario(usuario) >= 4){
            throw new OperacionNoPermitidaExc("Alcanzaste el máximo de 4 juegos favoritos");
        }

        if(usuarioFavoritoRepository.findByUsuarioAndPosicion(usuario, posicion).isPresent()){
            throw new OperacionNoPermitidaExc("La posición ya está ocupada");
        }

        UsuarioFavorito favorito = new UsuarioFavorito();
        favorito.setUsuario(usuario);
        favorito.setJuego(juego);
        favorito.setPosicion(posicion);
        usuarioFavoritoRepository.save(favorito);
        return getFavoritos(username);
    }

    public List<FavoritoResponseDTO> getFavoritos(String username){
        Usuario usuario = usuarioService.getUserByUsername(username);

        return usuarioFavoritoRepository.findByUsuarioOrderByPosicionDesc(usuario).stream()
                .map(favorito -> new FavoritoResponseDTO(
                        favorito.getPosicion(),
                        favorito.getJuego().getTitulo()))
                .toList();
    }

    public void eliminarFavorito(String username, Long juegoId){
        Usuario usuario = usuarioService.getUserByUsername(username);
        Juego juego = juegoRepository.findById(juegoId).orElseThrow(()-> new RecursoNoEncontradoExc("Juego no encontrado"));

        UsuarioFavorito favorito = usuarioFavoritoRepository.findByUsuarioAndJuego(usuario, juego).orElseThrow(()->new RecursoNoEncontradoExc("Favorito no encontrado"));

        usuarioFavoritoRepository.delete(favorito);
    }

}
