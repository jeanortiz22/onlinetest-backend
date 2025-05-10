package co.edu.uco.onlinetest.data.dao.entity.departamento;

import co.edu.uco.onlinetest.data.dao.entity.CreateDAO;
import co.edu.uco.onlinetest.data.dao.entity.DeleteDAO;
import co.edu.uco.onlinetest.data.dao.entity.RetrieveDAO;
import co.edu.uco.onlinetest.data.dao.entity.UpdateDAO;
import co.edu.uco.onlinetest.entity.DepartamentoEntity;
import co.edu.uco.onlinetest.entity.PaisEntity;

import java.util.UUID;

public interface DepartamentoDAO
        extends CreateDAO <DepartamentoEntity>, RetrieveDAO<DepartamentoEntity, UUID>, UpdateDAO<DepartamentoEntity, UUID>, DeleteDAO<UUID> {

}
