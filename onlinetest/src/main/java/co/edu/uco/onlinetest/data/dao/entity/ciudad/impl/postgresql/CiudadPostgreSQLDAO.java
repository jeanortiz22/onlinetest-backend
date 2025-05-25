package co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.postgresql;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.entity.CiudadEntity;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private final DataSource dataSource;

    public CiudadPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(CiudadEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO ciudad (id, nombre, departamento_id) VALUES (?, ?, ?)");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getNombre());
            sentenciaPreparada.setObject(3, entity.getDepartamento().getId());

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información de una nueva ciudad...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer INSERT en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de una nueva ciudad...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer INSERT en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM ciudad WHERE id = ?");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setObject(1, id);
            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la información de una ciudad...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer DELETE en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información de una ciudad...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer DELETE en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<CiudadEntity> listByFilter(CiudadEntity entity) throws OnlineTestException {
        var listaCiudades = new ArrayList<CiudadEntity>();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE 1=1");

        boolean filtrarPorId = entity != null
                && !UtilUUID.esValorDefecto(entity.getId());
        boolean filtrarPorNombre = entity != null
                && !UtilTexto.getInstance().estaVacia(entity.getNombre());
        // <-- aquí va la corrección:
        boolean filtrarPorDepartamento = entity != null
                && entity.getDepartamento() != null
                && !UtilUUID.esValorDefecto(entity.getDepartamento().getId());

        if (filtrarPorId) {
            sentenciaSQL.append(" AND LOWER (nombre)  LIKE LOWER (?)");
        }
        if (filtrarPorNombre) {
            sentenciaSQL.append(" AND nombre LIKE ?");
        }
        if (filtrarPorDepartamento) {
            sentenciaSQL.append(" AND departamento_id = ?");
        }

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            int index = 1;
            if (filtrarPorId) {
                sentenciaPreparada.setObject(index++, entity.getId());
            }
            if (filtrarPorNombre) {
                sentenciaPreparada.setString(index++, "%" + entity.getNombre().trim() + "%");
            }
            if (filtrarPorDepartamento) {
                sentenciaPreparada.setObject(index++, entity.getDepartamento().getId());
            }

            try (var resultado = sentenciaPreparada.executeQuery()) {
                while (resultado.next()) {
                    listaCiudades.add(construirCiudadDesdeResultado(resultado));
                }
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de la ciudad con los filtros deseados...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer SELECT en la tabla ciudad con filtros...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información de la ciudad...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla ciudad con filtros...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaCiudades;
    }

    @Override
    public List<CiudadEntity> listAll() throws OnlineTestException {
        var listaCiudades = new ArrayList<CiudadEntity>();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            var resultado = sentenciaPreparada.executeQuery();

            while (resultado.next()) {
                listaCiudades.add(construirCiudadDesdeResultado(resultado));
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema tratando de obtener la lista de ciudades.";
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de listar todas las ciudades en la base de datos.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha producido un problema inesperado tratando de obtener la lista de ciudades.";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de listar todas las ciudades en la base de datos.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaCiudades;
    }

    @Override
    public CiudadEntity listById(UUID id) throws OnlineTestException {
        var ciudadEntityRetorno = new CiudadEntity();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, departamento_id FROM ciudad WHERE id = ?");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setObject(1, id);

            try (var resultado = sentenciaPreparada.executeQuery()) {
                if (resultado.next()) {
                    ciudadEntityRetorno = construirCiudadDesdeResultado(resultado);
                }
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información de la ciudad con el identificador deseado...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer SELECT en la tabla ciudad por ID.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información de la ciudad...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla ciudad por ID...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return ciudadEntityRetorno;
    }

    
    @Override
    public void updateById(UUID id, CiudadEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE ciudad SET nombre = ?, departamento_id = ? WHERE id = ?");

        try {
            Connection conexion = dataSource.getConnection();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setString(1, entity.getNombre());
            sentenciaPreparada.setObject(2, entity.getDepartamento().getId());
            sentenciaPreparada.setObject(3, id);

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de una ciudad...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer UPDATE en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información de una ciudad...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer UPDATE en la tabla ciudad...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private CiudadEntity construirCiudadDesdeResultado(ResultSet resultado) throws SQLException {
        var ciudad = new CiudadEntity();
        ciudad.setId(UtilUUID.convertirAUUID(resultado.getString("id")));
        ciudad.setNombre(resultado.getString("nombre"));

        var departamento = new DepartamentoEntity();
        departamento.setId(UtilUUID.convertirAUUID(resultado.getString("departamento_id")));
        ciudad.setDepartamento(departamento);

        return ciudad;
    }

}

