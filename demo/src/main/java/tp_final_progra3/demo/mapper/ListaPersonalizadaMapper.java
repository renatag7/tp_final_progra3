package tp_final_progra3.demo.mapper;

import org.mapstruct.Mapper;
import tp_final_progra3.demo.model.dto.request.ListaPersonalizadaRequestDTO;
import tp_final_progra3.demo.model.dto.response.ListaPersonalizadaResponseDTO;
import tp_final_progra3.demo.model.entity.ListaPersonalizada;

@Mapper
public interface ListaPersonalizadaMapper {
    ListaPersonalizadaResponseDTO ToDto (ListaPersonalizada  listaPersonalizada);
    ListaPersonalizada ToEntity (ListaPersonalizadaRequestDTO listaPersonalizadaRequestDTO);
}
