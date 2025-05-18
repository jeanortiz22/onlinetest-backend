package co.edu.uco.onlinetest.businesslogic.bussinesslogic;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;
import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;

import java.util.List;
import java.util.UUID;

public interface PaisBusinessLogic {

    void registrarNuevoPais(PaisDomain pais) throws OnlineTestException;

    void modificarPaisExistente(UUID id, PaisDomain pais) throws OnlineTestException;

    void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException;

    PaisDomain consultarPaisPorId(UUID id) throws OnlineTestException;

    List <PaisDomain> consultarPaises(PaisDomain filtro) throws OnlineTestException;

}
