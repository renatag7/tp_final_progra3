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
    date = "2026-05-28T17:40:40-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class JuegoMapperImpl implements JuegoMapper {

    @Override
    public JuegoResponseDTO toDTO(Juego juego) {
        if ( juego == null ) {
            return null;
        }

        String titulo = null;
        LocalDate fecha_lanzamiento = null;
        Double rating_general = null;
        Set<String> generos = null;
        Set<String> plataformas = null;
        String developer = null;

        titulo = juego.getTitulo();
        fecha_lanzamiento = juego.getFecha_lanzamiento();
        rating_general = juego.getRating_general();
        generos = mapGenerosResponse( juego.getGeneros() );
        plataformas = mapPlataformasResponse( juego.getPlataformas() );
        developer = juego.getDeveloper();

        Long id = null;
        String descripcion = null;

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
        juego.setSinopsis( juegoRequestDTO.sinopsis() );
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
        juego.setSinopsis( juegoApiResponseDTO.description() );
        juego.setRating_general( juegoApiResponseDTO.rating() );
        juego.setGeneros( generoApiDTOSetToGeneroSet( juegoApiResponseDTO.genres() ) );
        juego.setPlataformas( plataformaApiDTOSetToPlataformaSet( juegoApiResponseDTO.platforms() ) );

        juego.setFecha_lanzamiento( juegoApiResponseDTO.released() == null ? null : java.time.LocalDate.parse(juegoApiResponseDTO.released()) );
        juego.setDeveloper( juegoApiResponseDTO.developers().isEmpty() ? null : juegoApiResponseDTO.developers().iterator().next().name() );

        return juego;
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

    @Override
    public Plataforma toPlataforma(PlataformaApiDTO plataformaApiDTO) {
        if ( plataformaApiDTO == null ) {
            return null;
        }

        Plataforma plataforma = new Plataforma();

        plataforma.setNombre( plataformaApiDTO.name() );

        return plataforma;
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

    protected Set<Plataforma> plataformaApiDTOSetToPlataformaSet(Set<PlataformaApiDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Plataforma> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( PlataformaApiDTO plataformaApiDTO : set ) {
            set1.add( toPlataforma( plataformaApiDTO ) );
        }

        return set1;
    }
}
