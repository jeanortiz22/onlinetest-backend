package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.PaisAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class
PaisFacadeImpl implements PaisFacade {

    private DAOFactory daoFactory;
    private PaisBusinessLogic paisBusinessLogic;

    public PaisFacadeImpl() throws OnlineTestException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoPais(PaisDTO pais) throws OnlineTestException{

        try {

            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain
            PaisDomain paisDomain = PaisAssembler.toDomain(pais);

            paisBusinessLogic.registrarNuevoPais(paisDomain);

            daoFactory.confirmarTransacion();
        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de registrar el nuevo Pais. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarPaisExistente(UUID id, PaisDTO pais) throws OnlineTestException {

        try {
            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain
            PaisDomain paisDomain = PaisAssembler.toDomain(pais);

            // Modificar el dominio
            paisBusinessLogic.modificarPaisExistente(id, paisDomain);

            daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información del pais con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de modificar la información del Pais con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            paisBusinessLogic.darBajaDefinitivamentePaisExistente(id);

            daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información del pais con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de eliminar la información del Pais con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public PaisDTO consultarPaisPorId(UUID id) throws OnlineTestException{

        try {
            daoFactory.iniciarTransacion();

           //consultar el dominio
            PaisDomain paisDomainResultado = paisBusinessLogic.consultarPaisPorId(id);

            if (paisDomainResultado == null) {
                return null;
            }
            // Convertir de Domain a DTO
            PaisDTO paisDTOResultado = PaisAssembler.toDTO( paisDomainResultado);

            daoFactory.confirmarTransacion();

            return paisDTOResultado;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {

            daoFactory.cancelarTransacion();
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del pais con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de consultar la información del Pais con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<PaisDTO> consultarPaises(PaisDTO filtro) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain (para filtro)
            PaisDomain filtroDomain = PaisAssembler.toDomain(filtro);

            // Consultar la lista de dominios
            List<PaisDomain> dominios = paisBusinessLogic.consultarPaises(filtroDomain);

            // Convertir manualmente lista de Domain a lista de DTO
            List<PaisDTO> dtos = new ArrayList<>();
            if (dominios != null) {
                    for (PaisDomain domain : dominios) {
                        dtos.add(PaisAssembler.toDTO(domain));
                    }
            }
            daoFactory.confirmarTransacion();

            return dtos;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar los países con el filtro proporcionado...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de consultar los países. Revise el log de errores para más detalles.";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
