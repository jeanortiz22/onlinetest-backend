package co.edu.uco.onlinetest.businesslogic.assembler.pais.entity;

import co.edu.uco.onlinetest.businesslogic.assembler.EntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.ArrayList;
import java.util.List;

public final class PaisEntityAssembler implements EntityAssembler<PaisEntity, PaisDomain> {

    private static final PaisEntityAssembler INSTANCE = new PaisEntityAssembler();

    private PaisEntityAssembler() {
        super();
    }

    public static PaisEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public PaisEntity toEntity(PaisDomain domain) {
        return  UtilObjeto.getIntance().esNulo(domain)
                ? PaisEntity.obtenerValorDefecto()
                    : new PaisEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public PaisDomain toDomain(PaisEntity entity) {
        var paisEntityAEnsamblar = PaisEntity.obtenerValorDefecto(entity);
        return new PaisDomain(paisEntityAEnsamblar.getId(), paisEntityAEnsamblar.getNombre());
    }

    @Override
    public List<PaisDomain> toDomain(List<PaisEntity> entityList) {
        var listaResultados = new ArrayList<PaisDomain>();

        for (PaisEntity paisEntity : entityList) {
            listaResultados.add(toDomain(paisEntity));
        }
        return listaResultados;
    }

    @Override
    public List<PaisEntity> toEntity(List<PaisDomain> domainList) {
        var listaResultados = new ArrayList<PaisEntity>();

        if (UtilObjeto.getIntance().esNulo(domainList) || domainList.isEmpty()) {
            return listaResultados;
        }

        for (PaisDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }

        return listaResultados;
    }
}
