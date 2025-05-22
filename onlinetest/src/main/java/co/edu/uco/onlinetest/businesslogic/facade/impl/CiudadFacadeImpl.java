package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.CiudadAssembler;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CiudadFacadeImpl implements CiudadFacade {

    private DAOFactory daoFactory;
    private CiudadBusinessLogic ciudadBusinessLogic;

    public CiudadFacadeImpl() throws OnlineTestException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        ciudadBusinessLogic = new CiudadBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevaCiudad(CiudadDTO ciudad) throws OnlineTestException{
        try {

            daoFactory.iniciarTransacion();

            CiudadDomain ciudadDomain = CiudadAssembler.toDomain(ciudad);

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

            CiudadDomain ciudadDomain = CiudadAssembler.toDomain(ciudad);

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
            if (ciudadDomainResultado == null) {
                daoFactory.confirmarTransacion();
                return null;
            }

            CiudadDTO ciudadDTOResultado = CiudadAssembler.toDTO(ciudadDomainResultado);

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
            CiudadDomain filtroDomain = CiudadAssembler.toDomain(filtro);

            // Consultar la lista de dominios
            List<CiudadDomain> dominios = ciudadBusinessLogic.consultarCiudad(filtroDomain);

            // Convertir manualmente lista de Domain a lista de DTO
            List<CiudadDTO> dtos = new ArrayList<>();
            if (dominios != null) {
                for (CiudadDomain domain : dominios) {
                    dtos.add(CiudadAssembler.toDTO(domain));
                }
            }
            daoFactory.confirmarTransacion();

            return dtos;

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
