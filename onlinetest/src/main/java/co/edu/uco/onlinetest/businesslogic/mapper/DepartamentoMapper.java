package co.edu.uco.onlinetest.businesslogic.mapper;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class DepartamentoMapper {

    public static DepartamentoEntity toEntity(DepartamentoDomain domain) {
        if (domain == null) return null;

        DepartamentoEntity entity = new DepartamentoEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        // Convertir PaisDomain a PaisEntity
        entity.setPais(PaisMapper.toEntity(domain.getPais()));
        return entity;
    }

    public static DepartamentoDomain toDomain(DepartamentoEntity entity) {
        if (entity == null) return null;

        return new DepartamentoDomain(
            entity.getId(),
            entity.getNombre(),
            PaisMapper.toDomain(entity.getPais())
        );
    }
}