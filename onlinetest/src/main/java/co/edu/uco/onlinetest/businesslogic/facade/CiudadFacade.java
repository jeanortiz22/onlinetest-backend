package co.edu.uco.onlinetest.businesslogic.facade;

import co.edu.uco.onlinetest.dto.CiudadDTO;

import java.util.UUID;
import java.util.List;

public interface CiudadFacade {

    void registrarNuevaCiudad(CiudadDTO ciudad);

    void modificarCiudadExistente(UUID id, CiudadDTO ciudad);

    void darBajaDefinitivamenteCiudadExistente(UUID id);

    CiudadDTO consultarCiudadPorId(UUID id);

    List<CiudadDTO> consultarCiudades(CiudadDTO filtro);
}
