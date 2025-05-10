package co.edu.uco.onlinetest.data.dao.entity;

public interface UpdateDAO <E, ID> {

    void updateById(ID id, E entity);

}
