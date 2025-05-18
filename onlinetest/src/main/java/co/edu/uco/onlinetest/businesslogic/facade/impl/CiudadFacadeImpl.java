package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.CiudadBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.CiudadFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.CiudadDTO;

import java.util.List;
import java.util.UUID;

public class CiudadFacadeImpl implements CiudadFacade {

    private DAOFactory daoFactory;
    private CiudadBusinessLogic ciudadBusinessLogic;

    public CiudadFacadeImpl() throws OnlineTestException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        ciudadBusinessLogic = new CiudadBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevaCiudad(CiudadDTO ciudad) {

    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDTO ciudad) {

    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) {

    }

    @Override
    public CiudadDTO consultarCiudadPorId(UUID id) {
        return null;
    }

    @Override
    public List<CiudadDTO> consultarCiudades(CiudadDTO filtro) {
        return List.of();
    }
}
