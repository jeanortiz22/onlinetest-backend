package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.pais.entity.CiudadEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.CiudadEntity;


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
        validarIntegridadInformacionRegistrarNuevaCiudad(ciudad);
        validarNoExistaCiudadConMismoNombre(ciudad.getNombre());

        var id = generarIdentificadorNuevaCiudad();
        var ciudadDomainACrear = new CiudadDomain(id, ciudad.getNombre(), ciudad.getDepartamento());

        var ciudadEntity = CiudadEntityAssembler.getInstance().toEntity(ciudadDomainACrear);
        factory.getCiudadDAO().create(ciudadEntity);
    }

    private void validarIntegridadInformacionRegistrarNuevaCiudad(CiudadDomain ciudad) throws OnlineTestException {
        var nombre = ciudad.getNombre();
        if (UtilTexto.getInstance().estaVacia(nombre)) {
            throw BusinessLogicOnlineTestException.reportar("El nombre de la ciudad es obligatorio...");
        }

        if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombre)) {
            throw BusinessLogicOnlineTestException.reportar("El nombre de la ciudad solo puede contener letras...");
        }

        if (!UtilTexto.getInstance().longitudValida(nombre, 1, 50)) {
            throw BusinessLogicOnlineTestException.reportar("El nombre de la ciudad supera los 50 caracteres...");
        }
    }

    private void validarNoExistaCiudadConMismoNombre(String nombreCiudad) throws OnlineTestException {
        var filtro = new CiudadEntity();
        filtro.setNombre(nombreCiudad);

        var listaResultados = factory.getCiudadDAO().listByFilter(filtro);

        if (!listaResultados.isEmpty()) {
            throw BusinessLogicOnlineTestException.reportar("Ya existe una ciudad con el nombre: " + nombreCiudad + "...");
        }
    }

    private UUID generarIdentificadorNuevaCiudad() throws OnlineTestException {
        UUID nuevoId;
        var existeId = false;

        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            var ciudad = factory.getCiudadDAO().listById(nuevoId);
            existeId = !UtilUUID.esValorDefecto(ciudad.getId());
        } while (existeId);

        return nuevoId;
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDomain ciudad) throws OnlineTestException {
        var ciudadExistente = factory.getCiudadDAO().listById(id);
        if (UtilUUID.esValorDefecto(ciudadExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("La ciudad con id: " + id + " no existe...");
        }

        validarIntegridadInformacionRegistrarNuevaCiudad(ciudad);

        CiudadEntity ciudadEntity = CiudadEntityAssembler.getInstance().toEntity(ciudad);
        factory.getCiudadDAO().updateById(id, ciudadEntity);
    }

    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException {
        var ciudadExistente = factory.getCiudadDAO().listById(id);
        if (UtilUUID.esValorDefecto(ciudadExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("La ciudad con id: " + id + " no existe...");
        }

        factory.getCiudadDAO().delete(id);
    }

    @Override
    public CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException {
        var ciudadEntity = factory.getCiudadDAO().listById(id);
        return CiudadEntityAssembler.getInstance().toDomain(ciudadEntity);
    }


    @Override
    public List<CiudadDomain> consultarCiudad(CiudadDomain filtro) throws OnlineTestException {
        var ciudadFiltro = CiudadEntityAssembler.getInstance().toEntity(filtro);

        System.out.println("filtro: " + ciudadFiltro.getNombre());
        List<CiudadEntity> ciudadEntities = factory.getCiudadDAO().listByFilter(ciudadFiltro);

        return CiudadEntityAssembler.getInstance().toDomain(ciudadEntities);
    }
}
