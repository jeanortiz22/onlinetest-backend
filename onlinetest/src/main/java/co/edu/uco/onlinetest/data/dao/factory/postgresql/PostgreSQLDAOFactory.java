package co.edu.uco.onlinetest.data.dao.factory.postgresql;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.postgresql.CiudadPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.data.dao.entity.departamento.impl.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.data.dao.entity.pais.impl.postgresql.PaisPostgreSQLDAO;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDAOFactory extends DAOFactory {

    private Connection conexion;
    private boolean transaccionEstaIniciada;
    private boolean conexionEstaAbierta;

    public PostgreSQLDAOFactory() throws OnlineTestException {
        abrirConexion();
        transaccionEstaIniciada = false;
        conexionEstaAbierta = false;
    }

    @Override
    protected void abrirConexion() throws OnlineTestException {

        var baseDatos = "ONLINETESTDB";
        var servidor = "";

        try {
            DriverManager.getConnection("");
            conexionEstaAbierta = true;
        }
        catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de obtener la conexión con la fuente de datos para llevar a cabo la operación deseada...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de obtener la conexión con la fuente de datos "+baseDatos+"@"+servidor+".Para tener mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de obtener la conexión con la fuente de datos para llevar a cabo la operación deseada...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo SQLException tratando de obtener la conexión con la fuente de datos "+baseDatos+"@"+servidor+".Para tener mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void iniciarTransacion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            conexion.setAutoCommit(false);
            transaccionEstaIniciada = true;
        }
        catch (OnlineTestException exception) {
            throw exception;
        }
        catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de iniciar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de iniciar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de iniciar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo SQLException tratando de iniciar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void confirmarTransacion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.commit();
        }
        catch (OnlineTestException exception) {
            throw exception;
        }
        catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de confirmar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de confirmar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

            throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de confirmar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo SQLException tratando de confirmar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

           throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cancelarTransacion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.rollback();
        } catch (OnlineTestException exception) {
            throw exception;
        }catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de cancelar la transacción  con la fuente de datos para llevar a cabo la operación deseada...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de cancelar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

          throw   DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de cancelar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo SQLException tratando de cancelar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cerrarConexion() throws OnlineTestException {
        try {
            asegurarConexionAbierta();
            conexion.close();
        }
        catch (OnlineTestException exception) {
            throw exception;
        }
        catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de cerrar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de cerrar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

           throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de cerrar la transacción  con la fuente de datos para llevar a cabo la operacion deseada...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo SQLException tratando de cerrar la transacción sobre la conexión con la base de datos. Para mas detalles, revise el log de errores...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private void asegurarTransaccionIniciada() throws OnlineTestException {
        if (!transaccionEstaIniciada) {
            var mensajeUsuario = "Se ha presentado un problema tratando de gestionar la transacción  con la fuente de datos para llevar a cabo la operación deseada...";
            var mensajeTecnico = "Se intento gestionar(COMMIT/ROLLBACK) una transacción que no ha sido iniciada.";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, new Exception());
        }
    }

    private void asegurarConexionAbierta() throws OnlineTestException {
        if (!conexionEstaAbierta) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar a cabo la operación deseada con una conexión cerrada...";
            var mensajeTecnico = "Se intento llevar a cabo una operación que requería una conexión abierta, pero al momento de validar la conexión estaba cerrada.";

            throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, new Exception());
        }
    }

    @Override
    public PaisDAO getPaisDAO() throws OnlineTestException {
        asegurarConexionAbierta();
        return new PaisPostgreSQLDAO(conexion);
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() throws OnlineTestException {
        asegurarConexionAbierta();
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public CiudadDAO getCiudadDAO() throws OnlineTestException{
        asegurarConexionAbierta();
        return new CiudadPostgreSQLDAO(conexion);
    }
}
