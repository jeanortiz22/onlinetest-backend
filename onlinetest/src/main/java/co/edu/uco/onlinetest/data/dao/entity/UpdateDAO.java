package co.edu.uco.onlinetest.data.dao.entity;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface UpdateDAO <E, ID> {

    void updateById(ID id, E entity) throws OnlineTestException;

}
