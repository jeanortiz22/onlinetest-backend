package co.edu.uco.onlinetest.businesslogic.assembler;

import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;

public class DepartamentoAssembler {

    public static DepartamentoDomain toDomain(DepartamentoDTO dto) {
        if (dto == null) return null;

        return new DepartamentoDomain(
                dto.getId(),
                dto.getNombre(),
                PaisAssembler.toDomain(dto.getPais())  // dto.getPais() retorna PaisDTO
        );
    }

    public static DepartamentoDTO toDTO(DepartamentoDomain domain) {
        if (domain == null) return null;

        return new DepartamentoDTO(
                domain.getId(),
                domain.getNombre(),
                PaisAssembler.toDTO(domain.getPais())
        );
    }
}
