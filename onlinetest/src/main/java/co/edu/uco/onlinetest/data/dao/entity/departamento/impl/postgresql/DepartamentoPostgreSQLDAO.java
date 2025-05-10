package co.edu.uco.onlinetest.data.dao.entity.departamento.impl.postgresql;

import co.edu.uco.onlinetest.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class DepartamentoPostgreSQLDAO implements DepartamentoDAO {

    private Connection conexion;

    public DepartamentoPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(DepartamentoEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<DepartamentoEntity> listByFilter(DepartamentoEntity filter) {
        return List.of();
    }

    @Override
    public List<DepartamentoEntity> listAll() {
        return List.of();
    }

    @Override
    public DepartamentoEntity listById(UUID uuid) {
        return null;
    }

    @Override
    public void updateById(UUID uuid, DepartamentoEntity entity) {

    }
}
