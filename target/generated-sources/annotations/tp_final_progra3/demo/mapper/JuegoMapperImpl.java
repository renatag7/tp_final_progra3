package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.api.GeneroApiDTO;
import tp_final_progra3.demo.model.dto.api.JuegoApiResponseDTO;
import tp_final_progra3.demo.model.dto.api.PlataformaApiDTO;
import tp_final_progra3.demo.model.dto.request.JuegoRequestDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.entity.Genero;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.Plataforma;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-29T00:37:39-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class JuegoMapperImpl implements JuegoMapper {

    @Override
    public JuegoResponseDTO toDTO(Juego juego) {
        if ( juego == null ) {
            return null;
        }

        Set<String> plataformas = null;
        Long id = null;
        String titulo = null;
        String descripcion = null;
        LocalDate fecha_lanzamiento = null;
        Double rating_general = null;
        Set<String> generos = null;
        String developer = null;

        plataformas = mapPlataformasResponse( juego.getPlataformas() );
        id = juego.getId();
        titulo = juego.getTitulo();
        descripcion = juego.getDescripcion();
        fecha_lanzamiento = juego.getFecha_lanzamiento();
        rating_general = juego.getRating_general();
        generos = mapGenerosResponse( juego.getGeneros() );
        developer = juego.getDeveloper();

        JuegoResponseDTO juegoResponseDTO = new JuegoResponseDTO( id, titulo, descripcion, fecha_lanzamiento, rating_general, generos, plataformas, developer );

        return juegoResponseDTO;
    }

    @Override
    public Juego toEntity(JuegoRequestDTO juegoRequestDTO) {
        if ( juegoRequestDTO == null ) {
            return null;
        }

        Juego juego = new Juego();

        juego.setPlataformas( mapPlataformasRequest( juegoRequestDTO.plataformas() ) );
        juego.setGeneros( mapGenerosRequest( juegoRequestDTO.generos() ) );
        juego.setTitulo( juegoRequestDTO.titulo() );
        juego.setDescripcion( juegoRequestDTO.descripcion() );
        juego.setFecha_lanzamiento( juegoRequestDTO.fecha_lanzamiento() );
        juego.setDeveloper( juegoRequestDTO.developer() );

        return juego;
    }

    @Override
    public Juego fromApi(JuegoApiResponseDTO juegoApiResponseDTO) {
        if ( juegoApiResponseDTO == null ) {
            return null;
        }

        Juego juego = new Juego();

        juego.setTitulo( juegoApiResponseDTO.name() );
        juego.setDescripcion( juegoApiResponseDTO.description() );
        juego.setRating_general( juegoApiResponseDTO.rating() );
        juego.setGeneros( generoApiDTOSetToGeneroSet( juegoApiResponseDTO.genres() ) );
        juego.setPlataformas( mapPlataformas( juegoApiResponseDTO.platforms() ) );
        juego.setId( juegoApiResponseDTO.id() );

        juego.setFecha_lanzamiento( juegoApiResponseDTO.released() == null ? null : java.time.LocalDate.parse(juegoApiResponseDTO.released()) );
        juego.setDeveloper( juegoApiResponseDTO.developers() == null || juegoApiResponseDTO.developers().isEmpty() ? null : juegoApiResponseDTO.developers().iterator().next().name() );

        return juego;
    }

    @Override
    public Plataforma toPlataforma(PlataformaApiDTO plataformaApiDTO) {
        if ( plataformaApiDTO == null ) {
            return null;
        }

        Plataforma plataforma = new Plataforma();

        plataforma.setNombre( plataformaApiDTO.name() );
        plataforma.setId( plataformaApiDTO.id() );

        return plataforma;
    }

    @Override
    public Genero toGenero(GeneroApiDTO generoApiDTO) {
        if ( generoApiDTO == null ) {
            return null;
        }

        Genero genero = new Genero();

        genero.setNombre( generoApiDTO.name() );

        return genero;
    }

    protected Set<Genero> generoApiDTOSetToGeneroSet(Set<GeneroApiDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Genero> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( GeneroApiDTO generoApiDTO : set ) {
            set1.add( toGenero( generoApiDTO ) );
        }

        return set1;
    }
}
