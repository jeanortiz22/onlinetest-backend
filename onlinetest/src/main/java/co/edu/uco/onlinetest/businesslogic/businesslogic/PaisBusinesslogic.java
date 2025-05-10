package co.edu.uco.onlinetest.businesslogic.businesslogic;

import java.util.UUID;

import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domian.PaisDomain;

public interface PaisBusinesslogic {
	
	void registrarNuevoPais (PaisDTO pais);
	
	void modificarPaisExistente (UUID id, PaisDTO pais);
	
	void darBajaDefinitivamentePaisExistente (UUID id);
	
	PaisDomain consultarPaisPorId (UUID id);
	
	list <PaisDomain> consultarPaises (PaisDTO filtro);

}
