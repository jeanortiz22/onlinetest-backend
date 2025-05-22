package co.edu.uco.onlinetest.businesslogic.mapper;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.entity.CiudadEntity;

public class CiudadMapper {

    public static CiudadEntity toEntity(CiudadDomain domain) {
        if (domain == null) return null;

        CiudadEntity entity = new CiudadEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        // Convertir DepartamentoDomain a DepartamentoEntity
        entity.setDepartamento(DepartamentoMapper.toEntity(domain.getDepartamento()));
        return entity;
    }

    public static CiudadDomain toDomain(CiudadEntity entity) {
        if (entity == null) return null;

        return new CiudadDomain(
                entity.getId(),
                entity.getNombre(),
                DepartamentoMapper.toDomain(entity.getDepartamento())
        );
    }
}
