package co.edu.uco.onlinetest.businesslogic.assembler;

import co.edu.uco.onlinetest.dto.PaisDTO;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;

public class PaisAssembler {

    // Convertir de DTO a Domain
    public static PaisDomain toDomain(PaisDTO dto) {
        if (dto == null) return null;

        return new PaisDomain(dto.getId(), dto.getNombre());
    }

    // Convertir de Domain a DTO
    public static PaisDTO toDTO(PaisDomain domain) {
        if (domain == null) return null;

        return new PaisDTO(domain.getId(), domain.getNombre());
    }
}
