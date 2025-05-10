package co.edu.uco.onlinetest.data.dao.entity.pais;

import co.edu.uco.onlinetest.data.dao.entity.CreateDAO;

public interface PaisDAO extends CreateDAO <PaisDomain>, RetrieveDAO <PaisDomain, UUID>, UpdateDAO <PaisDomain, UUID>, DeleteDAO<UUID> {

}
