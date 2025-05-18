package co.edu.uco.onlinetest.businesslogic.facade;

import co.edu.uco.onlinetest.crosscutting.excepciones.OnlineTestException;
import co.edu.uco.onlinetest.dto.PaisDTO;

import java.util.List;
import java.util.UUID;

public interface PaisFacade {

    void registrarNuevoPais(PaisDTO pais) throws OnlineTestException;

    void modificarPaisExistente(UUID id, PaisDTO pais) throws OnlineTestException;

    void darBajaDefinitivamentePaisExistente(UUID id) throws OnlineTestException;

    PaisDTO consultarPaisPorId(UUID id) throws OnlineTestException;

    List <PaisDTO> consultarPaises(PaisDTO filtro) throws OnlineTestException;



}
