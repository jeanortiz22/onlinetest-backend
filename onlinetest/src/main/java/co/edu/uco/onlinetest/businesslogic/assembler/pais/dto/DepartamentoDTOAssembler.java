package co.edu.uco.onlinetest.businesslogic.assembler.pais.dto;

import co.edu.uco.onlinetest.businesslogic.assembler.DTOAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.ArrayList;
import java.util.List;


public class DepartamentoDTOAssembler implements DTOAssembler<DepartamentoDTO, DepartamentoDomain> {

    private static final DepartamentoDTOAssembler INSTANCE = new DepartamentoDTOAssembler();

    private DepartamentoDTOAssembler() {
        super();
    }

    public static DepartamentoDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoDTO toDTO(final DepartamentoDomain domain) {
        return UtilObjeto.getIntance().esNulo(domain)
                ? DepartamentoDTO.obtenerValorDefecto()
                : new DepartamentoDTO(
                domain.getId(),
                domain.getNombre(),
                PaisDTOAssembler.getInstance().toDTO(domain.getPais())
        );
    }

    @Override
    public DepartamentoDomain toDomain(final DepartamentoDTO dto) {
        if (UtilObjeto.getIntance().esNulo(dto)) {
            return null;
        }
        return new DepartamentoDomain(
                dto.getId(),
                dto.getNombre(),
                PaisDTOAssembler.getInstance().toDomain(dto.getPais())
        );
    }

    @Override
    public List<DepartamentoDomain> toDomain(final List<DepartamentoDTO> dtoList) {
        if (UtilObjeto.getIntance().esNulo(dtoList) || dtoList.isEmpty()) {
            return new ArrayList<>();
        }
        List<DepartamentoDomain> domainList = new ArrayList<>();
        for (DepartamentoDTO dto : dtoList) {
            DepartamentoDomain domain = toDomain(dto);
            if (domain != null) {
                domainList.add(domain);
            }
        }
        return domainList;
    }

    @Override
    public List<DepartamentoDTO> toDTO(final List<DepartamentoDomain> domainList) {
        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return new ArrayList<>();
        }
        List<DepartamentoDTO> dtoList = new ArrayList<>();
        for (DepartamentoDomain domain : domainList) {
            dtoList.add(toDTO(domain));
        }
        return dtoList;
    }
}

