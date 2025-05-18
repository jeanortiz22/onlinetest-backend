package co.edu.uco.onlinetest.data.dao.entity;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface DeleteDAO <ID> {
    void delete(ID id) throws OnlineTestException;
}
