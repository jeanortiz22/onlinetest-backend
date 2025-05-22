package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.mapper.CiudadMapper;
import co.edu.uco.onlinetest.businesslogic.mapper.DepartamentoMapper;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CiudadBusinessLogicImpl implements CiudadBusinessLogic {

    private DAOFactory factory;

    public CiudadBusinessLogicImpl(DAOFactory factory) throws OnlineTestException {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoCiudad(CiudadDomain ciudad) throws OnlineTestException {
        CiudadEntity ciudadEntity = CiudadMapper.toEntity(ciudad); //  magia de traducir de domain a entity
        factory.getCiudadDAO().create(ciudadEntity);
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException {
        CiudadEntity ciudadEntity = CiudadMapper.toEntity(ciudad);
        factory.getCiudadDAO().updateById(id,ciudadEntity);
    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException{
        factory.getCiudadDAO().delete(id);
    }

    @Override
    public CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException{
        CiudadEntity entity = factory.getCiudadDAO().listById(id);

        if (entity == null) {
            return null;
        }

        return CiudadMapper.toDomain(entity);
    }

    @Override
    public List<CiudadDomain> consultarCiudad(CiudadDomain filtro) throws OnlineTestException{
        CiudadEntity ciudadFilter = filtro == null ? null : CiudadMapper.toEntity(filtro);

        List<CiudadEntity> ciudadEntities = factory.getCiudadDAO().listByFilter(ciudadFilter);
        List<CiudadDomain> datosARetornar = new ArrayList<>();

        if (ciudadEntities != null) {
            Iterator<CiudadEntity> iterador = ciudadEntities.iterator();
            while (iterador.hasNext()) {
                CiudadEntity entity = iterador.next();
                datosARetornar.add(CiudadMapper.toDomain(entity));
            }
        }
        return datosARetornar;
    }
}
