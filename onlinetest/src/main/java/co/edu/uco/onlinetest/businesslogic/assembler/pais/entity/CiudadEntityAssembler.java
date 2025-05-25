package co.edu.uco.onlinetest.businesslogic.assembler.pais.entity;

import co.edu.uco.onlinetest.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.CiudadEntity;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

import java.util.ArrayList;
import java.util.List;

public final class CiudadEntityAssembler implements EntityAssembler<CiudadEntity, CiudadDomain> {

    private static final CiudadEntityAssembler INSTANCE = new CiudadEntityAssembler();

    private CiudadEntityAssembler() {
        super();
    }

    public static CiudadEntityAssembler getInstance() {
        return INSTANCE;
    }


    @Override
    public CiudadEntity toEntity(CiudadDomain domain) {
        return UtilObjeto.getIntance().esNulo(domain)
                ? CiudadEntity.obtenerValorDefecto()
                : new CiudadEntity(domain.getId(), domain.getNombre(),
                DepartamentoEntityAssembler.getInstance().toEntity(domain.getDepartamento()));
    }

    @Override
    public CiudadDomain toDomain(CiudadEntity entity) {
        var ciudadEntityAEnsamblar = CiudadEntity.obtenerValorDefecto(entity);
        return new CiudadDomain(ciudadEntityAEnsamblar.getId(),
                ciudadEntityAEnsamblar.getNombre(),
                DepartamentoEntityAssembler.getInstance().toDomain(ciudadEntityAEnsamblar.getDepartamento()));
    }

    @Override
    public List<CiudadDomain> toDomain(List<CiudadEntity> entityList) {
        var listaResultados = new ArrayList<CiudadDomain>();

        for (CiudadEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<CiudadEntity> toEntity(List<CiudadDomain> domainList) {
        var listaResultados = new ArrayList<CiudadEntity>();

        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return listaResultados;
        }

        for (CiudadDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }

        return listaResultados;
    }
}
