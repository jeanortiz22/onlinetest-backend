package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.mapper.CiudadMapper;
import co.edu.uco.onlinetest.businesslogic.mapper.DepartamentoMapper;
import co.edu.uco.onlinetest.businesslogic.mapper.PaisMapper;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

    public class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

    private DAOFactory factory;

    public DepartamentoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException {
        DepartamentoEntity departamentoEntity = DepartamentoMapper.toEntity(departamento); //  magia de traducir de domain a entity
        factory.getDepartamentoDAO().create(departamentoEntity);
    }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException {
        DepartamentoEntity departamentoEntity = DepartamentoMapper.toEntity(departamento);
        factory.getDepartamentoDAO().updateById(id, departamentoEntity);
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException {
        factory.getDepartamentoDAO().delete(id);
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException {

        DepartamentoEntity entity = factory.getDepartamentoDAO().listById(id);

        if (entity == null) {
            return null;
        }

        return DepartamentoMapper.toDomain(entity);
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamento(DepartamentoDomain filtro) throws OnlineTestException {

        DepartamentoEntity departamentoFilter = filtro == null ? null : DepartamentoMapper.toEntity(filtro);

        List<DepartamentoEntity> departamentoEntities = factory.getDepartamentoDAO().listByFilter(departamentoFilter);
        List<DepartamentoDomain> datosARetornar = new ArrayList<>();

        if (departamentoEntities != null) {
            Iterator<DepartamentoEntity> iterador = departamentoEntities.iterator();
            while (iterador.hasNext()) {
                DepartamentoEntity entity = iterador.next();
                datosARetornar.add(DepartamentoMapper.toDomain(entity));
            }
        }

        return datosARetornar;
    }
}
