package tp_final_progra3.demo.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.JuegoResponseDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.model.entity.Genero;
import tp_final_progra3.demo.model.entity.Juego;
import tp_final_progra3.demo.model.entity.ListaPersonalizada;
import tp_final_progra3.demo.model.entity.Plataforma;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-18T21:45:54-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class ListaPersonalizadaMapperImpl implements ListaPersonalizadaMapper {

    @Override
    public ListaPersonalizadaResponseDTO ToDto(ListaPersonalizada listaPersonalizada) {
        if ( listaPersonalizada == null ) {
            return null;
        }

        Long id_lista = null;
        String nombre = null;
        String descripcion = null;
        LocalDate fechaCreacion = null;
        List<JuegoResponseDTO> juegos = null;

        id_lista = listaPersonalizada.getId_lista();
        nombre = listaPersonalizada.getNombre();
        descripcion = listaPersonalizada.getDescripcion();
        if ( listaPersonalizada.getFechaCreacion() != null ) {
            fechaCreacion = listaPersonalizada.getFechaCreacion().toLocalDate();
        }
        juegos = juegoSetToJuegoResponseDTOList( listaPersonalizada.getJuegos() );

        ListaPersonalizadaResponseDTO listaPersonalizadaResponseDTO = new ListaPersonalizadaResponseDTO( id_lista, nombre, descripcion, fechaCreacion, juegos );

        return listaPersonalizadaResponseDTO;
    }

    @Override
    public ListaPersonalizada ToEntity(ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO) {
        if ( listaPersonalizadaRequestDTO == null ) {
            return null;
        }

        ListaPersonalizada listaPersonalizada = new ListaPersonalizada();

        listaPersonalizada.setNombre( listaPersonalizadaRequestDTO.nombre() );
        listaPersonalizada.setDescripcion( listaPersonalizadaRequestDTO.descripcion() );
        if ( listaPersonalizadaRequestDTO.esPublica() != null ) {
            listaPersonalizada.setEsPublica( listaPersonalizadaRequestDTO.esPublica() );
        }

        return listaPersonalizada;
    }

    protected Set<String> generoSetToStringSet(Set<Genero> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Genero genero : set ) {
            set1.add( map( genero ) );
        }

        return set1;
    }

    protected Set<String> plataformaSetToStringSet(Set<Plataforma> set) {
        if ( set == null ) {
            return null;
        }

        Set<String> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Plataforma plataforma : set ) {
            set1.add( map( plataforma ) );
        }

        return set1;
    }

    protected JuegoResponseDTO juegoToJuegoResponseDTO(Juego juego) {
        if ( juego == null ) {
            return null;
        }

        Long id = null;
        String titulo = null;
        String descripcion = null;
        LocalDate fecha_lanzamiento = null;
        Double rating_general = null;
        Set<String> generos = null;
        Set<String> plataformas = null;
        String developer = null;

        id = juego.getId();
        titulo = juego.getTitulo();
        descripcion = juego.getDescripcion();
        fecha_lanzamiento = juego.getFecha_lanzamiento();
        rating_general = juego.getRating_general();
        generos = generoSetToStringSet( juego.getGeneros() );
        plataformas = plataformaSetToStringSet( juego.getPlataformas() );
        developer = juego.getDeveloper();

        JuegoResponseDTO juegoResponseDTO = new JuegoResponseDTO( id, titulo, descripcion, fecha_lanzamiento, rating_general, generos, plataformas, developer );

        return juegoResponseDTO;
    }

    protected List<JuegoResponseDTO> juegoSetToJuegoResponseDTOList(Set<Juego> set) {
        if ( set == null ) {
            return null;
        }

        List<JuegoResponseDTO> list = new ArrayList<JuegoResponseDTO>( set.size() );
        for ( Juego juego : set ) {
            list.add( juegoToJuegoResponseDTO( juego ) );
        }

        return list;
    }
}
