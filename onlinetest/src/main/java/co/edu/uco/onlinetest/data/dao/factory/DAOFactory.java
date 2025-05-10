package co.edu.uco.onlinetest.data.dao.factory;

import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.factory.postgresql.PostgreSQLDAOFactory;

public abstract class DAOFactory {

    public static DAOFactory getFactory(Factory factory) {

        switch (factory) {
            case POSTGRE_SQL:
                return new PostgreSQLDAOFactory() {
                };
            default:
                throw new IllegalArgumentException("Unexpected value: " + factory);
        }
    }

    protected abstract void abrirConexion();

    public abstract void iniciarTransacion();

    public abstract void confirmarTransacion();

    public abstract void cancelarTransacion();

    public abstract void cerrarConexion();

    public abstract PaisDAO getPaisDAO();

    public abstract DepartamentoDAO getDepartamentoDAO();

    public abstract CiudadDAO getCiudadDAO();

}
