package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.assembler.DepartamentoAssembler;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.DepartamentoBusinessLogic;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.impl.DepartamentoBusinessLogicImpl;
import co.edu.uco.onlinetest.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.onlinetest.crosscutting.excepciones.BusinessLogicOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DepartamentoFacadeImpl implements DepartamentoFacade {

    private DAOFactory daoFactory;
    private  DepartamentoBusinessLogic departamentoBusinessLogic;

    public DepartamentoFacadeImpl() throws OnlineTestException {
        this.daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        this.departamentoBusinessLogic = new DepartamentoBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoDepartamento(DepartamentoDTO departamento) throws OnlineTestException {
        try {

            daoFactory.iniciarTransacion();

            DepartamentoDomain departamentoDomain = DepartamentoAssembler.toDomain(departamento);

            departamentoBusinessLogic.registrarNuevoDepartamento(departamentoDomain);

            daoFactory.confirmarTransacion();
        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de un nuevo departamento...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de registrar el nuevo departamento. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarDepartamentoExistente(UUID id, DepartamentoDTO departamento) throws OnlineTestException {

        try {
            daoFactory.iniciarTransacion();
            // Convertir manualmente de DTO a Domain
            DepartamentoDomain departamentoDomain = DepartamentoAssembler.toDomain(departamento);

            // Modificar el dominio
            departamentoBusinessLogic.modificarDepartamentoExistente(id, departamentoDomain);

            daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información del departamento con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de modificar la información del departamento con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            departamentoBusinessLogic.darBajaDefinitivamenteDepartamentoExistente(id);

            daoFactory.confirmarTransacion();

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información del departamento con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de eliminar la información del departamento con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public DepartamentoDTO consultarDepartamentoPorId(UUID id) throws OnlineTestException {
        try {
            daoFactory.iniciarTransacion();

            //consultar el dominio
            DepartamentoDomain departamentoDomainResultado = departamentoBusinessLogic.consultarDepartamentoPorId(id);

            if (departamentoDomainResultado == null) {
                return null;
            }
            // Convertir de Domain a DTO
            DepartamentoDTO departamentoDTOResultado = DepartamentoAssembler.toDTO(departamentoDomainResultado);
            daoFactory.confirmarTransacion();

            return departamentoDTOResultado;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {

            daoFactory.cancelarTransacion();
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del departamento con el identificador deseado...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de consultar la información del departamento con el id deseado. Para mas detalles revise el log de errores...";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro) throws OnlineTestException{
        try {
            daoFactory.iniciarTransacion();

            // Convertir manualmente de DTO a Domain (para filtro)
            DepartamentoDomain filtroDomain = DepartamentoAssembler.toDomain(filtro);

            // Consultar la lista de dominios
            List<DepartamentoDomain> dominios = departamentoBusinessLogic.consultarDepartamento(filtroDomain);

            // Convertir manualmente lista de Domain a lista de DTO
            List<DepartamentoDTO> dtos = new ArrayList<>();
            if (dominios != null) {
                for (DepartamentoDomain domain : dominios) {
                    dtos.add(DepartamentoAssembler.toDTO(domain));
                }
            }
            daoFactory.confirmarTransacion();

            return dtos;

        } catch (OnlineTestException exception) {
            daoFactory.cancelarTransacion();
            throw exception;

        } catch (Exception exception) {
            daoFactory.cancelarTransacion();

            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar los departamentos con el filtro proporcionado...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA de tipo Exception tratando de consultar los departamentos. Revise el log de errores para más detalles.";

            throw BusinessLogicOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

}

