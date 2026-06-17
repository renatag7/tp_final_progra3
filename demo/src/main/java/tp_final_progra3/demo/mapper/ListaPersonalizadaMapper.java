package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.model.entity.Genero;
import tp_final_progra3.demo.model.entity.ListaPersonalizada;
import tp_final_progra3.demo.model.entity.Plataforma;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListaPersonalizadaMapper {
    ListaPersonalizadaResponseDTO ToDto (ListaPersonalizada  listaPersonalizada);
    @Mapping(target = "usuario", ignore = true)
    ListaPersonalizada ToEntity (ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO);

    default String map(Genero genero) {
        return genero.getNombre();
    }

    default String map(Plataforma plataforma) {
        return plataforma.getNombre();
    }
}
