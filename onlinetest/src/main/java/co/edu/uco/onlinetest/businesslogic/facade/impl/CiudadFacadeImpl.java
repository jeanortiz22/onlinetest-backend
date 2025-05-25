package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.pais.dto.CiudadDTOAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.CiudadBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.CiudadBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.CiudadFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.CiudadDTO;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import co.edu.uco.onlinetest.dto.PaisDTO;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class CiudadFacadeImpl implements CiudadFacade {

    private final DataSource dataSource;
    private DAOFactory daoFactory;
    private CiudadBusinessLogic ciudadBusinessLogic;

    public CiudadFacadeImpl(DataSource dataSource) throws OnlineTestException {
        this.dataSource = dataSource;
        try{
            DAOFactory.setDataSource(dataSource);
            daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
            this.ciudadBusinessLogic = new CiudadBusinessLogicImpl(daoFactory);
    }catch (Exception exception){
            throw new IllegalStateException("Error inicializando DAOFactory en DepartamentoFacadeImpl", exception);

        }
    }

    @Override
    public void registrarNuevaCiudad(CiudadDTO ciudad) throws OnlineTestException{
        try {

            daoFactory.iniciarTransacion();

            CiudadDomain ciudadDomain = CiudadDTOAssembler.getInstance().toDomain(ciudad);

            ciudadBusinessLogic.registrarNuevoCiudad(ciudadDomain);

            daoFactory.confirmarTransacion();
        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de un nueva ciudad...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de registrar el nueva ciudad. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarCiudadExistente(UUID id, CiudadDTO ciudad) throws OnlineTestException {
        try {
            daoFactory.iniciarTransacion();

            CiudadDomain ciudadDomain = CiudadDTOAssembler.getInstance().toDomain(ciudad);

            // Modificar el dominio
             ciudadBusinessLogic.modificarCiudadExistente(id, ciudadDomain);

            daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información de la ciudad con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de modificar la información de la ciudad con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }


    @Override
    public void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException{

        try {
        daoFactory.iniciarTransacion();

        ciudadBusinessLogic.darBajaDefinitivamenteCiudadExistente(id);

        daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información de la ciudad con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de eliminar la información de la ciudad con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public CiudadDTO consultarCiudadPorId(UUID id) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            //consultar el dominio
            CiudadDomain ciudadDomainResultado = ciudadBusinessLogic.consultarCiudadPorId(id);

            // Convertir de Domain a DTO
            CiudadDTO ciudadDTOResultado = CiudadDTOAssembler.getInstance().toDTO(ciudadDomainResultado);

            daoFactory.confirmarTransacion();

            return ciudadDTOResultado;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {

            daoFactory.cancelarTransacion();
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información de la ciudad con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de consultar la información de la ciudad con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<CiudadDTO> consultarCiudades(CiudadDTO filtro) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain (para filtro)
            CiudadDomain filtroDomain = CiudadDTOAssembler.getInstance().toDomain(filtro);

            // Consultar la lista de dominios
            var dominios = ciudadBusinessLogic.consultarCiudad(filtroDomain);

            var dominiosResultado = CiudadDTOAssembler.getInstance().toDTO(dominios);

            daoFactory.confirmarTransacion();

            return dominiosResultado;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar las ciudades con el filtro proporcionado...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de consultar las ciudades. Revise el log de errores para más detalles.";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
