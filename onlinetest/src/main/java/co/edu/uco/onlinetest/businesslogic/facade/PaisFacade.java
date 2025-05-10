package co.edu.uco.onlinetest.businesslogic.facade;

import java.util.UUID;

import java.util.List;

import co.edu.uco.onlinetest.businesslogic.businesslogic.domian.PaisDomain;

public interface PaisFacade {
	
	void registrarNuevoPais (PaisDomain pais);
	
	void modificarPaisExistente (UUID id, PaisDomain pais);
	
	void darBajaDefinitivamentePaisExistente (UUID id);
	
	PaisDomain consultarPaisPorId (UUID id);
	
	list <PaisDomain> consultarPaises (PaisDomain filtro);

}
