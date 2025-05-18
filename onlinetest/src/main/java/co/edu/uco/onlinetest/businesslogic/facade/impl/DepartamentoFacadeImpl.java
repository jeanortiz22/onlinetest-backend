package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.DepartamentoBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;

import java.util.List;
import java.util.UUID;

public class DepartamentoFacadeImpl implements DepartamentoFacade {

    private DAOFactory daoFactory;
    private  DepartamentoBusinessLogic departamentoBusinessLogic;

    public DepartamentoFacadeImpl() throws OnlineTestException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        departamentoBusinessLogic = new DepartamentoBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDTO departamento) {

    }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDTO departamento) {

    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) {

    }

    @Override
    public DepartamentoDTO consultarDepartamentoPorId(UUID id) {
        return null;
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro) {
        return List.of();
    }
}
