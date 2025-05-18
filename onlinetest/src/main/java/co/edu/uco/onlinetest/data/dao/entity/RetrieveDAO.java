package co.edu.uco.onlinetest.data.dao.entity;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

import java.util.List;

public interface RetrieveDAO <E, ID> {

    List <E> listByFilter (E filter) throws OnlineTestException;

    List <E> listAll () throws OnlineTestException;

    E listById (ID id) throws OnlineTestException;
}
