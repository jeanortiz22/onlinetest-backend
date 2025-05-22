package co.edu.uco.onlinetest.businesslogic.assembler;

import co.edu.uco.onlinetest.dto.CiudadDTO;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;

public class CiudadAssembler {

    public static CiudadDomain toDomain(CiudadDTO dto) {
        if (dto == null) return null;

        return new CiudadDomain(
                dto.getId(),
                dto.getNombre(),
                DepartamentoAssembler.toDomain(dto.getDepartamento())  // dto.getDepartamento() es DepartamentoDTO
        );
    }

    public static CiudadDTO toDTO(CiudadDomain domain) {
        if (domain == null) return null;

        return new CiudadDTO(
                domain.getId(),
                domain.getNombre(),
                DepartamentoAssembler.toDTO(domain.getDepartamento())
        );
    }
}
