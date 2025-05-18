package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

import java.util.List;
import java.util.UUID;

    public class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

    private DAOFactory factory;

    public DepartamentoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException {
        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        factory.getDepartamentoDAO().create(departamentoEntity);
    }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException {
        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        factory.getDepartamentoDAO().updateById(id, departamentoEntity);
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException {
        factory.getDepartamentoDAO().delete(id);
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(UUID id) {
        return null;
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamento(DepartamentoDomain filtro) throws OnlineTestException {

        DepartamentoEntity departamentoEntity = new DepartamentoEntity();
        List<DepartamentoEntity> departamentoEntities = factory.getDepartamentoDAO().listByFilter(departamentoEntity);

        List<DepartamentoDomain> datosARetornar = null;

        return List.of();
    }
}
