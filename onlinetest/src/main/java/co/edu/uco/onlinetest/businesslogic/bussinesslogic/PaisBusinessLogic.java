package co.edu.uco.onlinetest.businesslogic.bussinesslogic;

import co.edu.uco.onlinetest.businesslogic.bussinesslogic.domain.PaisDomain;

import java.util.List;
import java.util.UUID;

public interface PaisBusinessLogic {

    void registrarNuevoPais(PaisDomain pais);

    void modificarPaisExistente(UUID id, PaisDomain pais);

    void darBajaDefinitivamentePaisExistente(UUID id);

    PaisDomain consultarPaisPorId(UUID id);

    List <PaisDomain> consultarPaises(PaisDomain filtro);



}
