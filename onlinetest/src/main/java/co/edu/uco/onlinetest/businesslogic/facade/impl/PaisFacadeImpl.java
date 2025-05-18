package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.List;
import java.util.UUID;

public class PaisFacadeImpl implements PaisFacade {

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

            PaisDomain paisDomain = null; //convertir de domain a entity
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

    }

    @Override
    public void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException{

    }

    @Override
    public PaisDTO consultarPaisPorId(UUID id) throws OnlineTestException{

        try {

            PaisDomain paisDomain = null; //convertir de domain a entity
            var paisDomainResultado = paisBusinessLogic.consultarPaisPorId(id);

            // Magia de convertir de domain a dto de respuesta
            return null;

        } catch (OnlineTestException exception) {
            throw exception;

        } catch (Exception exception) {

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del pais con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de consultar la información del Pais con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<PaisDTO> consultarPaises(PaisDTO filtro) throws OnlineTestException{
        return List.of();
    }
}
