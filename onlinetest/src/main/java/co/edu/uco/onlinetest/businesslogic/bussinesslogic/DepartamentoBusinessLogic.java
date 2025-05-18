package co.edu.uco.onlinetest.businesslogic.bussinesslogic;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

import java.util.List;
import java.util.UUID;

public interface DepartamentoBusinessLogic {

        void registrarNuevoDepartamento(DepartamentoDomain pais) throws OnlineTestException;

        void modificarDepartamentoExistente(UUID id, DepartamentoDomain pais) throws OnlineTestException;

        void darBajaDefinitivamenteDepartamentoExistente(UUID id) throws OnlineTestException;

        DepartamentoDomain consultarDepartamentoPorId(UUID id)throws OnlineTestException;

        List<DepartamentoDomain> consultarDepartamento(DepartamentoDomain filtro)throws OnlineTestException;

}

