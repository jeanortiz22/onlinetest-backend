package co.edu.uco.onlinetest.data.dao.factory;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.factory.postgresql.PostgreSQLDAOFactory;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory) throws OnlineTestException {

        switch (factory) {
            case POSTGRE_SQL:
                return new PostgreSQLDAOFactory() {
                };
            default:
                var mensajeUsuario = "Se ha presentado un problema tratando de obtener la información de la fuente de datos contra la cual se llevaran a cabo las operaciones...";
                var mensajeTecnico = "Se solicito la factoria "+factory+" pero no se tiene implementada en el sistema...";
                throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    protected abstract void abrirConexion() throws OnlineTestException;

    public abstract void iniciarTransacion() throws OnlineTestException;

    public abstract void confirmarTransacion() throws OnlineTestException;

    public abstract void cancelarTransacion() throws OnlineTestException;

    public abstract void cerrarConexion() throws OnlineTestException;

    public abstract PaisDAO getPaisDAO() throws OnlineTestException;

    public abstract DepartamentoDAO getDepartamentoDAO() throws OnlineTestException;

    public abstract CiudadDAO getCiudadDAO() throws OnlineTestException;

}
