package co.edu.uco.onlinetest.businesslogic.assembler.pais.dto;

import co.edu.uco.onlinetest.businesslogic.assembler.DTOAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.ArrayList;
import java.util.List;

public class PaisDTOAssembler implements DTOAssembler<PaisDTO, PaisDomain> {

    private  static final PaisDTOAssembler INSTANCE = new PaisDTOAssembler();

    private PaisDTOAssembler() {
        super();
    }

    public static PaisDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public PaisDTO toDTO( final PaisDomain domain) {
        return UtilObjeto.getIntance().esNulo(domain)
                ? PaisDTO.obtenerValorDefecto()
                : new PaisDTO(domain.getId(), domain.getNombre());

    }

    @Override
    public PaisDomain toDomain( final PaisDTO dto) {
        if (UtilObjeto.getIntance().esNulo(dto)) {
            return null; // Aquí retornamos null porque no hay valor por defecto para Domain
        }
        return new PaisDomain(dto.getId(), dto.getNombre());
    }

    @Override
    public List<PaisDomain> toDomain(final List<PaisDTO> dtoList) {
        if (UtilObjeto.getIntance().esNulo(dtoList) || dtoList.isEmpty()) {
            return new ArrayList<>();
        }
        List<PaisDomain> domainList = new ArrayList<>();
        for (PaisDTO dto : dtoList) {
            PaisDomain domain = toDomain(dto);
            if(domain != null) {
                domainList.add(domain);
            }
        }
        return domainList;
    }

    @Override
    public List<PaisDTO> toDTO(final List<PaisDomain> domainList) {
        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return new ArrayList<>();
        }
        List<PaisDTO> dtoList = new ArrayList<>();
        for (PaisDomain domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}

