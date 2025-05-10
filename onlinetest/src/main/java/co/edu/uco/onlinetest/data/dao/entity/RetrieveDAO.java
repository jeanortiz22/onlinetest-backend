package co.edu.uco.onlinetest.data.dao.entity;

import java.util.List;

public interface RetrieveDAO <E, ID> {

    List <E> listByFilter (E filter);

    List <E> listAll ();

    E listById (ID id);
}
