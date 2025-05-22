package co.edu.uco.onlinetest.businesslogic.mapper;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.entity.PaisEntity;

public class PaisMapper {

    public static PaisEntity toEntity(PaisDomain domain) {
        if (domain == null) return null;

        PaisEntity entity = new PaisEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        return entity;
    }

    public static PaisDomain toDomain(PaisEntity entity) {
        if (entity == null) return null;

        return new PaisDomain(entity.getId(), entity.getNombre());
    }
}
