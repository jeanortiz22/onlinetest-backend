package co.edu.uco.onlinetest.data.dao.entity.departamento.impl.postgresql;

import co.edu.uco.onlinetest.crosscutting.excepciones.DataOnlineTestException;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private final DataSource dataSource;

    public DepartamentoPostgreSQLDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(DepartamentoEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO departamento (id, nombre, pais_id) VALUES (?, ?, ?)");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());
            sentenciaPreparada.setObject(1, entity.getId());
            sentenciaPreparada.setString(2, entity.getNombre());
            sentenciaPreparada.setObject(3, entity.getPais().getId());

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la información de un nuevo departamento...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer INSERT en la tabla departamento...";
                throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de registrar la información de un nuevo departamento...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer INSERT en la tabla departamento...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void delete(UUID id) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM departamento WHERE id = ?");

        try (Connection conexion = dataSource.getConnection();
             var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            sentenciaPreparada.setObject(1, id);

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la información de un departamento...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer DELETE en la tabla departamento...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de eliminar la información de un departamento...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer DELETE en la tabla departamento...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity entity) throws OnlineTestException {
        var listaDepartamentos = new ArrayList<DepartamentoEntity>();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, pais_id FROM departamento WHERE 1=1");

        boolean filtrarPorId = entity != null
                && !UtilUUID.esValorDefecto(entity.getId());
        boolean filtrarPorNombre = entity != null
                && !UtilTexto.getInstance().estaVacia(entity.getNombre());
        // <-- aquí va la corrección:
        boolean filtrarPorPais = entity != null
                && entity.getPais() != null
                && !UtilUUID.esValorDefecto(entity.getPais().getId());

        if (filtrarPorId) {
            sentenciaSQL.append(" AND id = ?");
        }
        if (filtrarPorNombre) {
            sentenciaSQL.append(" AND LOWER (nombre)  LIKE LOWER (?)");
        }
        if (filtrarPorPais) {
            sentenciaSQL.append(" AND pais_id = ?");
        }

        try ( Connection conexion = dataSource.getConnection();
              var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString())) {

            int index = 1;
            if (filtrarPorId) {
                sentenciaPreparada.setObject(index++, entity.getId());
            }
            if (filtrarPorNombre) {
                sentenciaPreparada.setString(index++, "%" + entity.getNombre().trim() + "%");
            }
            if (filtrarPorPais) {
                sentenciaPreparada.setObject(index++, entity.getPais().getId());
            }

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {
                while (cursorResultados.next()) {
                    listaDepartamentos.add(construirDepartamentoDesdeResultado(cursorResultados));
                }
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información del departamento con los filtros deseados...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer SELECT en la tabla departamento con filtros...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del departamento...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla departamento con filtros...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaDepartamentos;
    }


    @Override
    public List<DepartamentoEntity> listAll() throws OnlineTestException {
        var listaDepartamentos = new ArrayList<DepartamentoEntity>();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, pais_id FROM departamento");

        try ( Connection conexion = dataSource.getConnection();
              var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());
             var resultado = sentenciaPreparada.executeQuery()) {

            while (resultado.next()) {
                var departamento = new DepartamentoEntity();
                departamento.setId(UtilUUID.convertirAUUID(resultado.getString("id")));
                departamento.setNombre(resultado.getString("nombre"));

                var pais = new PaisEntity();
                pais.setId(UtilUUID.convertirAUUID(resultado.getString("pais")));
                departamento.setPais(pais);

                listaDepartamentos.add(departamento);
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema tratando de obtener la lista de departamentos.";
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException tratando de listar todos los departamentos en la base de datos.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha producido un problema inesperado tratando de obtener la lista de departamentos.";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de listar todos los departamentos en la base de datos.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return listaDepartamentos;
    }


    @Override
    public DepartamentoEntity listById(UUID id) throws OnlineTestException {
        var departamentoEntityRetorno = new DepartamentoEntity();
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("SELECT id, nombre, pais_id FROM departamento WHERE id = ?");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setObject(1, id);

            try (var cursorResultados = sentenciaPreparada.executeQuery()) {

                if (cursorResultados.next()) {
                    departamentoEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("id")));
                    departamentoEntityRetorno.setNombre(cursorResultados.getString("nombre"));

                    var pais = new PaisEntity();
                    pais.setId(UtilUUID.convertirAUUID(cursorResultados.getString("pais_id")));
                    departamentoEntityRetorno.setPais(pais);
                }
            }

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la información del departamento con el identificador deseado...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer SELECT en la tabla departamento por ID.";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de consultar la información del departamento...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer SELECT en la tabla departamento por ID...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

        return departamentoEntityRetorno;
    }

    @Override
    public void updateById(UUID id, DepartamentoEntity entity) throws OnlineTestException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE departamento SET nombre = ?, pais_id = ? WHERE id = ?");

        try {
            Connection conexion = dataSource.getConnection();
            var sentenciaPreparada = conexion.prepareStatement(sentenciaSQL.toString());

            sentenciaPreparada.setString(1, entity.getNombre());
            sentenciaPreparada.setObject(2, entity.getPais().getId());
            sentenciaPreparada.setObject(3, id);

            sentenciaPreparada.executeUpdate();

        } catch (SQLException exception) {
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la información de un departamento...";
            var mensajeTecnico = "Se presentó una excepción SQLException tratando de hacer UPDATE en la tabla departamento...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de modificar la información de un departamento...";
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer UPDATE en la tabla departamento...";
            throw DataOnlineTestException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private DepartamentoEntity construirDepartamentoDesdeResultado(ResultSet resultado) throws SQLException {
        var departamento = new DepartamentoEntity();
        departamento.setId(UtilUUID.convertirAUUID(resultado.getString("id")));
        departamento.setNombre(resultado.getString("nombre"));

        var pais = new PaisEntity();
        pais.setId(UtilUUID.convertirAUUID(resultado.getString("pais_id")));
        departamento.setPais(pais);

        return departamento;
    }
}
