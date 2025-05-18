package co.edu.uco.onlinetest.data.dao.entity;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

public interface CreateDAO <E> {
    void create(E entity) throws OnlineTestException;
}
