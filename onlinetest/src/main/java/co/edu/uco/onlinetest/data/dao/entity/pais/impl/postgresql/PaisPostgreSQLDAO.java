package co.edu.uco.onlinetest.data.dao.entity.pais.impl.postgresql;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class PaisPostgreSQLDAO implements PaisDAO {

    private Connection conexion;

    public PaisPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(PaisEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("INSERT INTO pais (id, nombre) VALUES (?,?)");

        try {
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getNombre());

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {

            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de hacer un INSERT en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de hacer un INSERT en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("DELETE FROM pais WHERE id = ?");

        try (
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            sentenciaPreparada.setObject(1, id);

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informaciÓn de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepciÓn de tipo SQLException tratando de hacer un DELETE en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de hacer un DELETE en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<PaisEntity> listByFilter(PaisEntity filter) {
        return null;
    }

    @Override
    public List<PaisEntity> listAll() {
        return null;
    }

    @Override
    public PaisEntity listById(UUID id) throws OnlineTestException {

        var paisEntityRetorno = new PaisEntity();
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT id, nombre FROM pais WHERE id = ?");

        try (
                var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            sentenciaPreparada.setObject(1, id);

            try (
                    var cursorResultado = sentenciaPreparada.executeQuery()) {

                if (cursorResultado.next()) {
                    paisEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultado.getString("id")));
                    paisEntityRetorno.setNombre(cursorResultado.getString("nombre"));
                }

            }

        }

        catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de hacer un SELECT en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de hacer un SELECT en la tabla pais...";

            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return paisEntityRetorno;
    }



    @Override
    public void updateById(UUID id, PaisEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("UPDATE pais SET nombre = ? WHERE id = ?");

        try (

            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            sentenciaPreparada.setString(1, entity.getNombre());
            sentenciaPreparada.setObject(2, id);

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción de tipo SQLException tratando de hacer un UPDATE en la tabla pais...";

            throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }catch ( Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información de un nuevo pais...";
            var mensajeTecnico = "Se presento un excepción NO CONTROLADA de tipo Exception tratando de hacer un UPDATE en la tabla pais...";

            throw  DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

    }
}

