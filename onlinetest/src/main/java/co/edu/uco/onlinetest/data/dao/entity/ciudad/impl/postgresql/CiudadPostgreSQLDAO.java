package co.edu.uco.onlinetest.data.dao.entity.ciudad.impl.postgresql;

import co.edu.uco.onlinetest.data.dao.entity.ciudad.CiudadDAO;
import co.edu.uco.onlinetest.entity.CiudadEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class CiudadPostgreSQLDAO implements CiudadDAO {

    private Connection conexion;

    public CiudadPostgreSQLDAO(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(CiudadEntity entity) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public List<CiudadEntity> listByFilter(CiudadEntity filter) {
        return List.of();
    }

    @Override
    public List<CiudadEntity> listAll() {
        return List.of();
    }

    @Override
    public CiudadEntity listById(UUID uuid) {
        return null;
    }

    @Override
    public void updateById(UUID uuid, CiudadEntity entity) {

    }
}
