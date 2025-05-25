package co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.pais.entity.PaisEntityAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.List;
import java.util.UUID;

public class PaisBusinessLogicImpl implements PaisBusinessLogic {

    private DAOFactory factory;

    public PaisBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }


    @Override
    public void registrarNuevoPais(PaisDomain pais) throws OnlineTestException {

        //1. Se debe asegurar que los datos sean validos a nivel de tipo de datos,
        //longitud, obligatoriedad, formato, rango.
        validarIntegridadInformacionRegistrarNuevoPais(pais);

        //2. Validar el nombre del pis no exista
        validarNoExistaPaisConMismoNombre(pais.getNombre());

        //3. Generar identificador nuevo pais
        var id = generarIdentificadorNuevoPais();

        //4. Recrear el domain con el nuevo id
        var paisDomainACrear = new PaisDomain(id, pais.getNombre());

        //5. Creamos el pais siempre y cuando no se hayan cumplido las validaciones
        var paisEntity = PaisEntityAssembler.getInstance().toEntity(paisDomainACrear);//  magia de traducir de domain a entity
        factory.getPaisDAO().create(paisEntity);
    }

    private void validarIntegridadInformacionRegistrarNuevoPais(PaisDomain pais) throws OnlineTestException {
        validarIntegridadNombrePais(pais.getNombre());
    }

    private void validarIntegridadNombrePais(String nombrePais) throws OnlineTestException {
        //Nombre Pais Obligatorio
        if (UtilTexto.getInstance().estaVacia(nombrePais)) {
            throw BusinessLogicOnlineTestException.reportar("El nombre del pais es obligatorio...");
        }
        //Nombre Pais tenga solo letras
        if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombrePais)) {
            throw BusinessLogicOnlineTestException.reportar("El nombre del pais solo puede contener letras...");
        }

        //Nombre cumpla con la longitud
        if (!UtilTexto.getInstance().longitudValida(nombrePais, 1,50)){
            throw BusinessLogicOnlineTestException.reportar("El nombre del pais supera los 50 caracteres");

        }
    }
    private void validarNoExistaPaisConMismoNombre(String nombrePais) throws OnlineTestException {

        var filtro = new PaisEntity();
        filtro.setNombre(nombrePais);

        var listaResultados = factory.getPaisDAO().listByFilter(filtro);

        //Si la lista devolvio resultados, entonces ya existe un pais con ese nombre
        if(!listaResultados.isEmpty()){
            throw BusinessLogicOnlineTestException.reportar("Ya existe un pais con el nombre: " + nombrePais + "...");
        }
    }

    private UUID generarIdentificadorNuevoPais() throws OnlineTestException {

        UUID nuevoId;
        var existeId = false;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            var pais = factory.getPaisDAO().listById(nuevoId);
            existeId = !UtilUUID.esValorDefecto(pais.getId());

        } while (existeId);

        return nuevoId;

    }

        @Override
    public void modificarPaisExistente(UUID id, PaisDomain pais) throws OnlineTestException{

        var paisExistente = factory.getPaisDAO().listById(id);
        if (UtilUUID.esValorDefecto(paisExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("No existe un pais con el id: " + id + "...");
        }

        validarIntegridadNombrePais(pais.getNombre());
        var paisEntity = PaisEntityAssembler.getInstance().toEntity(pais); //  magia de traducir de domain a entity
        factory.getPaisDAO().updateById(id, paisEntity);
    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException {
        var paisExistente = factory.getPaisDAO().listById(id);
        if (UtilUUID.esValorDefecto(paisExistente.getId())) {
            throw BusinessLogicOnlineTestException.reportar("No existe un pais con el id: " + id + "...");
        }
        factory.getPaisDAO().delete(id);
    }

    @Override
    public PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException {
       var paisEntity = factory.getPaisDAO().listById(id);
        return PaisEntityAssembler.getInstance().toDomain(paisEntity);
    }

    @Override
    public List<PaisDomain> consultarPaises(PaisDomain filtro) throws OnlineTestException{

        var paisFilter = PaisEntityAssembler.getInstance().toEntity(filtro);

        System.out.println("Filtro aplicado: " + paisFilter.getNombre());
        List<PaisEntity> paisEntities = factory.getPaisDAO().listByFilter(paisFilter);


        return PaisEntityAssembler.getInstance().toDomain(paisEntities);
    }
}
