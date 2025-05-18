package co.edu.uco.onlinetest.businesslogic.bussinesslogic;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.CiudadDomain;
import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.DepartamentoDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

import java.util.List;
import java.util.UUID;

public interface CiudadBusinessLogic {

    void registrarNuevoCiudad(CiudadDomain pais) throws OnlineTestException;

    void modificarCiudadExistente(UUID id, CiudadDomain pais) throws OnlineTestException;

    void darBajaDefinitivamenteCiudadExistente(UUID id) throws OnlineTestException;

    CiudadDomain consultarCiudadPorId(UUID id) throws OnlineTestException;

    List<CiudadDomain> consultarCiudad(CiudadDomain filtro) throws OnlineTestException;

}
