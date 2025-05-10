package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.List;
import java.util.UUID;

public class PaisFacadeImpl implements PaisFacade {

    private DAOFactory daoFactory;
    private PaisBusinessLogic paisBusinessLogic;

    public PaisFacadeImpl() {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoPais(PaisDTO pais) {

    }

    @Override
    public void modificarPaisExistente(UUID id, PaisDTO pais) {

    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) {

    }

    @Override
    public PaisDTO consultarPaisPorId(UUID id) {
        return null;
    }

    @Override
    public List<PaisDTO> consultarPaises(PaisDTO filtro) {
        return List.of();
    }
}
