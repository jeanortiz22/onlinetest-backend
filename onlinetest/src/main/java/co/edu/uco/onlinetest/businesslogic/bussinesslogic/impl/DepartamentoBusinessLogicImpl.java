package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.pais.entity.DepartamentoEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.assembler.pais.entity.PaisEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
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

        //1. Se debe asegurar que los datos sean validos a nivel de tipo de datos,
        //longitud, obligatoriedad, formato, rango.
        validarIntegridadInformacionRegistrarNuevoDepartamento(departamento);

        validarNoExistaDepartamentoConMismoNombre(departamento.getNombre());

        var  id = generarIdentificadorNuevoDepartamento();

        var departamentoDomainACrear = new DepartamentoDomain(id, departamento.getNombre(), departamento.getPais());

        //5. Creamos el departamento siempre y cuando no se hayan cumplido las validaciones
        var departamentoEntity = DepartamentoEntityAssembler.getInstance().toEntity(departamentoDomainACrear); //  magia de traducir de domain a entity
        factory.getDepartamentoDAO().create(departamentoEntity);
    }

        private void validarIntegridadInformacionRegistrarNuevoDepartamento(DepartamentoDomain departamento) throws OnlineTestException {
            validarIntegridadNombreDepartamento(departamento.getNombre());
        }

        private void validarIntegridadNombreDepartamento(String nombreDepartamento) throws OnlineTestException {
            // Nombre del Departamento obligatorio
            if (UtilTexto.getInstance().estaVacia(nombreDepartamento)) {
                throw BusinessLogicOnlineTestException.reportar("El nombre del departamento es obligatorio...");
            }

            // Solo letras y espacios
            if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombreDepartamento)) {
                throw BusinessLogicOnlineTestException.reportar("El nombre del departamento solo puede contener letras...");
            }

            // Longitud válida
            if (!UtilTexto.getInstance().longitudValida(nombreDepartamento, 1, 100)) {
                throw BusinessLogicOnlineTestException.reportar("El nombre del departamento supera los 50 caracteres...");
            }
        }

        private void validarNoExistaDepartamentoConMismoNombre(String nombreDepartamento) throws OnlineTestException {
            var filtro = new DepartamentoEntity();
            filtro.setNombre(nombreDepartamento);

            var listaResultados = factory.getDepartamentoDAO().listByFilter(filtro);

            if (!listaResultados.isEmpty()) {
                throw BusinessLogicOnlineTestException.reportar("Ya existe un departamento con el nombre: " + nombreDepartamento + "...");
            }
        }

        private UUID generarIdentificadorNuevoDepartamento() throws OnlineTestException {
            UUID nuevoId;
            var existeId = false;

            do {
                nuevoId = UtilUUID.generarNuevoUUID();
                var departamento = factory.getDepartamentoDAO().listById(nuevoId);
                existeId = !UtilUUID.esValorDefecto(departamento.getId());
            } while (existeId);

            return nuevoId;
        }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDomain departamento) throws OnlineTestException {

        var departamentoExistente = factory.getDepartamentoDAO().listById(id);
        if (UtilUUID.esValorDefecto(departamentoExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("El departamento con id: " + id + " no existe...");
        }

        validarIntegridadNombreDepartamento( departamento.getNombre());
        DepartamentoEntity departamentoEntity = DepartamentoEntityAssembler.getInstance().toEntity(departamento);
        factory.getDepartamentoDAO().updateById(id, departamentoEntity);
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException {
        var departamentoExistente = factory.getDepartamentoDAO().listById(id);
        if (UtilUUID.esValorDefecto(departamentoExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("El departamento con id: " + id + " no existe...");
        }
        factory.getDepartamentoDAO().delete(id);
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(UUID id) throws OnlineTestException {
        var paisEntity = factory.getDepartamentoDAO().listById(id);
            return DepartamentoEntityAssembler.getInstance().toDomain(paisEntity);
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamento(DepartamentoDomain filtro) throws OnlineTestException {

        var departamentoFiltro = DepartamentoEntityAssembler.getInstance().toEntity(filtro);

        System.out.println("Filtro aplicado: " + departamentoFiltro.getNombre());
        List<DepartamentoEntity> departamentoEntities = factory.getDepartamentoDAO().listByFilter(departamentoFiltro);

        return DepartamentoEntityAssembler.getInstance().toDomain(departamentoEntities);

    }
}
