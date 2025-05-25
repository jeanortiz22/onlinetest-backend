package co.edu.uco.onlinetest.data.dao.entity.pais.impl.postgresql;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaisPostgreSQLDAO implements PaisDAO {

    private final DataSource dataSource;

    public PaisPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(PaisEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("INSERT INTO pais (id, nombre) VALUES (?,?)");

        try {
            Connection conexion = dataSource.getConnection();
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

        try (Connection conexion = dataSource.getConnection();
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
    public List<PaisEntity> listByFilter(PaisEntity filter) throws OnlineTestException {
        var listaResultados = new ArrayList<PaisEntity>();
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT id, nombre FROM pais WHERE 1=1");

        boolean filtrarPorId = !UtilUUID.esValorDefecto(filter.getId());
        boolean filtrarPorNombre = !UtilTexto.getInstance().esValorDefecto(filter.getNombre());

        if (filtrarPorId) {
            sentenciaSQL.append(" AND id = ?");
        }
        if (filtrarPorNombre) {
            sentenciaSQL.append(" AND LOWER(nombre) LIKE LOWER(?)");
        }

        try (
                Connection conexion = dataSource.getConnection();
                var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            int index = 1;
            if (filtrarPorId) {
                sentenciaPreparada.setObject(index++, filter.getId());
            }
            if (filtrarPorNombre) {
                sentenciaPreparada.setString(index++, "%" + filter.getNombre().trim().toLowerCase() + "%");
            }

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    listaResultados.add(construirPaisDesdeResultado(cursorResultados));
                }
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información del país con los filtros deseados...";
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer SELECT en la tabla Pais por ID...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del país...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla Pais por ID...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }


    @Override
    public List<PaisEntity> listAll() throws OnlineTestException {

        var listaResultados = new ArrayList<PaisEntity>();
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("SELECT id, nombre FROM Pais ORDER BY nombre ASC");


        try (
                Connection conexion = dataSource.getConnection();
                var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());
                var cursorResultados = sentenciaPreparada.executeQuery()
        ) {
            while (cursorResultados.next()) {
                var paisEntityRetorno = new PaisEntity(); // O usar null si prefieres retornar null si no se encuentra
                paisEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                paisEntityRetorno.setNombre(cursorResultados.getString("nombre"));

                listaResultados.add(paisEntityRetorno);
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de los paises deseados...";
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer SELECT en la tabla Pais.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del países deseados...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla Pais...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaResultados;
    }


    @Override
    public PaisEntity listById(UUID id) throws OnlineTestException {

        var paisEntityRetorno = new PaisEntity(); // O usar null si prefieres retornar null si no se encuentra
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre FROM Pais WHERE id=?");

        try (Connection conexion = dataSource.getConnection();
                var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            sentenciaPreparada.setObject(1, id);

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                    if (cursorResultados.next()) {
                        paisEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                        paisEntityRetorno.setNombre(cursorResultados.getString("nombre"));
                    }
                }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información del país con el identificador deseado...";
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de hacer SELECT en la tabla Pais por ID.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del país...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla Pais por ID...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return paisEntityRetorno;
    }



        @Override
    public void updateById(UUID id, PaisEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();

        sentenciaSQL.append("UPDATE pais SET nombre = ? WHERE id = ?");

        try (
            Connection conexion = dataSource.getConnection();
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

    private PaisEntity construirPaisDesdeResultado(ResultSet resultado) throws SQLException {
        var pais = new PaisEntity();
        pais.setId(UtilUUID.convertirAUUID(resultado.getString("id")));
        pais.setNombre(resultado.getString("nombre"));
        return pais;
    }

}

