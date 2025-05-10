package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.List;
import java.util.UUID;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

    private DAOFactory factory;

    public PaisBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoPais(PaisDomain pais) {
        PaisEntity paisEntity = new PaisEntity();
        factory.getPaisDAO().create(paisEntity);
    }

    @Override
    public void modificarPaisExistente(UUID id, PaisDomain pais) {
        PaisEntity paisEntity = new PaisEntity();
        factory.getPaisDAO().updateById(id, paisEntity);
    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) {
        factory.getPaisDAO().delete(id);
    }

    @Override
    public PaisDomain consultarPaisPorId(UUID id) {
        return null;
    }

    @Override
    public List<PaisDomain> consultarPaises(PaisDomain filtro) {

        PaisEntity paisFilter = null;
        List<PaisEntity> paisEntities = factory.getPaisDAO().listByFilter(paisFilter);

        List<PaisDomain> datosARetornar = null;

        return datosARetornar;
    }
}
