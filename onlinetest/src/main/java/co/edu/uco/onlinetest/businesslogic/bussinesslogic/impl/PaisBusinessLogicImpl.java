package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.mapper.PaisMapper;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

    private DAOFactory factory;

    public PaisBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoPais(PaisDomain pais) throws OnlineTestException {
        PaisEntity paisEntity = PaisMapper.toEntity(pais);//  magia de traducir de domain a entity
        factory.getPaisDAO().create(paisEntity);
    }

    @Override
    public void modificarPaisExistente(UUID id, PaisDomain pais) throws OnlineTestException{
        PaisEntity paisEntity = PaisMapper.toEntity(pais); //  magia de traducir de domain a entity
        factory.getPaisDAO().updateById(id, paisEntity);
    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException {
        factory.getPaisDAO().delete(id);
    }

    @Override
    public PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException {
        PaisEntity entity = factory.getPaisDAO().listById(id);

        if (entity == null) {
            return null;
        }

        return PaisMapper.toDomain(entity);
    }

    @Override
    public List<PaisDomain> consultarPaises(PaisDomain filtro) throws OnlineTestException{

        PaisEntity paisFilter = (filtro == null) ? null : PaisMapper.toEntity(filtro);


        List<PaisEntity> paisEntities = factory.getPaisDAO().listByFilter(paisFilter);
        List<PaisDomain> datosARetornar = new ArrayList<>();

        if (paisEntities != null) {
            Iterator<PaisEntity> iterador = paisEntities.iterator();

            while (iterador.hasNext()) {
                PaisEntity entity = iterador.next();
                datosARetornar.add(PaisMapper.toDomain(entity));
            }
        }

        return datosARetornar;
    }
}
