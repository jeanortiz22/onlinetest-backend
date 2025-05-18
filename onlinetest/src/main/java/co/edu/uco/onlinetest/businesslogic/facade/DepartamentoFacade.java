package co.edu.uco.onlinetest.businesslogic.facade;

import co.edu.uco.onlinetest.dto.DepartamentoDTO;

import java.util.UUID;
import java.util.List;

public interface DepartamentoFacade {

    void registrarNuevoDepartamento(DepartamentoDTO departamento);

    void modificarDepartamentoExistente(UUID id, DepartamentoDTO departamento);

    void darBajaDefinitivamenteDepartamentoExistente(UUID id);

    DepartamentoDTO consultarDepartamentoPorId(UUID id);

    List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro);

}
