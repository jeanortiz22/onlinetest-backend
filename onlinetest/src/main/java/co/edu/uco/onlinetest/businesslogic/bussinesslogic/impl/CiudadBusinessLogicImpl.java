package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;

import java.util.List;
import java.util.UUID;

public class CiudadBusinessLogicImpl implements CiudadBusinessLogic {

    private DAOFactory factory;

    public CiudadBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoCiudad(CiudadDomain pais) throws OnlineTestException {
        CiudadEntity ciudadEntity = new CiudadEntity();
        factory.getCiudadDAO().create(ciudadEntity);
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDomain pais) throws OnlineTestException {
        CiudadEntity ciudadEntity = new CiudadEntity();
        factory.getCiudadDAO().updateById(id,ciudadEntity);
    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException {
        factory.getCiudadDAO().delete(id);
    }

    @Override
    public CiudadDomain consultarCiudadPorId(UUID id) {
        return null;
    }

    @Override
    public List<CiudadDomain> consultarCiudad(CiudadDomain filtro) throws OnlineTestException {
        CiudadEntity ciudadFilter = null;
        List<CiudadEntity> ciudadEntities = factory.getCiudadDAO().listByFilter(ciudadFilter);

        List<CiudadDomain> datosARetornar = null;

        return datosARetornar;
    }
}
