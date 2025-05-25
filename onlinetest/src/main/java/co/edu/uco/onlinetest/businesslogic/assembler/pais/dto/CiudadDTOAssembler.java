package co.edu.uco.onlinetest.businesslogic.assembler.pais.dto;

import co.edu.uco.onlinetest.businesslogic.assembler.DTOAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.dto.CiudadDTO;

import java.util.ArrayList;
import java.util.List;

public class CiudadDTOAssembler implements DTOAssembler<CiudadDTO, CiudadDomain> {

    private static final CiudadDTOAssembler INSTANCE = new CiudadDTOAssembler();

    private CiudadDTOAssembler() {
        super();
    }

    public static CiudadDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public CiudadDTO toDTO(final CiudadDomain domain) {
        return UtilObjeto.getIntance().esNulo(domain)
                ? CiudadDTO.obtenerValorDefecto()
                : new CiudadDTO(
                domain.getId(),
                domain.getNombre(),
                DepartamentoDTOAssembler.getInstance().toDTO(domain.getDepartamento())
        );
    }

    @Override
    public CiudadDomain toDomain(final CiudadDTO dto) {
        if (UtilObjeto.getIntance().esNulo(dto)) {
            return null;
        }
        return new CiudadDomain(
                dto.getId(),
                dto.getNombre(),
                DepartamentoDTOAssembler.getInstance().toDomain(dto.getDepartamento())
        );
    }

    @Override
    public List<CiudadDomain> toDomain(final List<CiudadDTO> dtoList) {
        if (UtilObjeto.getIntance().esNulo(dtoList) || dtoList.isEmpty()) {
            return new ArrayList<>();
        }
        List<CiudadDomain> domainList = new ArrayList<>();
        for (CiudadDTO dto : dtoList) {
            CiudadDomain domain = toDomain(dto);
            if (domain != null) {
                domainList.add(domain);
            }
        }
        return domainList;
    }

    @Override
    public List<CiudadDTO> toDTO(final List<CiudadDomain> domainList) {
        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return new ArrayList<>();
        }
        List<CiudadDTO> dtoList = new ArrayList<>();
        for (CiudadDomain domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}

