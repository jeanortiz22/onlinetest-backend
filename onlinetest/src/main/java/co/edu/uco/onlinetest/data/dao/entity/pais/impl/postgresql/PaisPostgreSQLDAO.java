package co.edu.uco.onlinetest.data.dao.entity.pais.impl.postgresql;

import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class PaisPostgreSQLDAO implements PaisDAO {

    private Connection conexion;

    public PaisPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(PaisEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

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
    public PaisEntity listById(UUID uuid) {
        return null;
    }

    @Override
    public void updateById(UUID uuid, PaisEntity entity) {

    }
}
