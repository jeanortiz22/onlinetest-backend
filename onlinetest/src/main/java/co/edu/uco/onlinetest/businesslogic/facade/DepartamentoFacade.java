package co.edu.uco.onlinetest.businesslogic.facade;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.DepartamentoDTO;

import java.util.UUID;
import java.util.List;

public interface DepartamentoFacade {

    void registrarNuevoDepartamento(DepartamentoDTO departamento) throws OnlineTestException;

    void modificarDepartamentoExistente(UUID id, DepartamentoDTO departamento) throws OnlineTestException;

    void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException;

    DepartamentoDTO consultarDepartamentoPorId(UUID id) throws OnlineTestException;

    List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro) throws OnlineTestException;

}
