package co.edu.uco.onlinetest.businesslogic.assembler.pais.entity;

import co.edu.uco.onlinetest.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

import java.util.ArrayList;
import java.util.List;

public final class DepartamentoEntityAssembler implements EntityAssembler<DepartamentoEntity, DepartamentoDomain> {

    private static final DepartamentoEntityAssembler INSTANCE = new DepartamentoEntityAssembler();

    private DepartamentoEntityAssembler() {
        super();
    }

    public static DepartamentoEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoEntity toEntity(DepartamentoDomain domain) {
        return UtilObjeto.getIntance().esNulo(domain)
                ? DepartamentoEntity.obtenerValorDefecto()
                : new DepartamentoEntity(domain.getId(), domain.getNombre(),
                PaisEntityAssembler.getInstance().toEntity(domain.getPais()));
    }

    @Override
    public DepartamentoDomain toDomain(DepartamentoEntity entity) {
        var departamentoEntityAEnsamblar = DepartamentoEntity.obtenerValorDefecto(entity);
        return new DepartamentoDomain(departamentoEntityAEnsamblar.getId(),
                departamentoEntityAEnsamblar.getNombre(),
                PaisEntityAssembler.getInstance().toDomain(departamentoEntityAEnsamblar.getPais()));
    }

    @Override
    public List<DepartamentoDomain> toDomain(List<DepartamentoEntity> entityList) {
        var listaResultados = new ArrayList<DepartamentoDomain>();

        for (DepartamentoEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<DepartamentoEntity> toEntity(List<DepartamentoDomain> domainList) {
        var listaResultados = new ArrayList<DepartamentoEntity>();

        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return listaResultados;
        }

        for (DepartamentoDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }

        return listaResultados;
    }
}
