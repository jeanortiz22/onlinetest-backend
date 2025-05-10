package co.edu.uco.onlinetest.data.dao.factory.postgresql;

import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.postgresql.CiudadPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.impl.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.impl.postgresql.PaisPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;

import java.sql.Connection;

public class PostgreSQLDAOFactory extends DAOFactory {

    private Connection conexion;

    public PostgreSQLDAOFactory() {
        abrirConexion();
    }

    @Override
    protected void abrirConexion() {
        conexion = null;
    }

    @Override
    public void iniciarTransacion() {

    }

    @Override
    public void confirmarTransacion() {

    }

    @Override
    public void cancelarTransacion() {

    }

    @Override
    public void cerrarConexion() {

    }

    @Override
    public PaisDAO getPaisDAO() {
        return new PaisPostgreSQLDAO(conexion);
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() {
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public CiudadDAO getCiudadDAO() {
        return new CiudadPostgreSQLDAO(conexion);
    }
}
