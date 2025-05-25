package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.pais.dto.PaisDTOAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.PaisBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.PaisFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.PaisDTO;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Service
public class PaisFacadeImpl implements PaisFacade {

    private final DataSource dataSource;
    private DAOFactory daoFactory;
    private PaisBusinessLogic paisBusinessLogic;

    public PaisFacadeImpl(DataSource dataSource) throws OnlineTestException {
        this.dataSource = dataSource;
        try {
            DAOFactory.setDataSource(dataSource);
            daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
            this.paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
        } catch (Exception exception) {
            throw new IllegalStateException("Error inicializando DAOFactory en PaisFacadeImpl", exception);
        }
    }

    @Override
    public void registrarNuevoPais(PaisDTO pais) throws OnlineTestException{

        try {

            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain
            var paisDomain = PaisDTOAssembler.getInstance().toDomain(pais);

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
            var paisDomain = PaisDTOAssembler.getInstance().toDomain(pais);

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

            // Convertir de Domain a DTO
            var paisDTOResultado = PaisDTOAssembler.getInstance().toDTO(paisDomainResultado);

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
            var filtroDomain = PaisDTOAssembler.getInstance().toDomain(filtro);

            // Consultar la lista de dominios
            var dominios = paisBusinessLogic.consultarPaises(filtroDomain);

            var dominiosResultado = PaisDTOAssembler.getInstance().toDTO(dominios);


            daoFactory.confirmarTransacion();

            return dominiosResultado;


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
