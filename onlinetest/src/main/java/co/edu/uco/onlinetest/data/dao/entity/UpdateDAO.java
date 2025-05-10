package co.edu.uco.onlinetest.data.dao.entity;

public interface UpdateDAO <E, ID> {

    void update(ID id,E entity);

}

