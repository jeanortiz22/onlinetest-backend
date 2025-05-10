package co.edu.uco.onlinetest.businesslogic.businesslogic.domian;

import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public final class PaisDomain {

	private UUID id;
	private String nombre;
	
	PaisDomain() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		
	}
	
	public PaisDomain(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}
	
	public PaisDomain(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
		
	}
	
	static PaisDomain obtenerValorDefecto() {
		return new PaisDomain();
	}
	
	static PaisDomain obtenerValorDefecto(final PaisDomain pais) {
		return UtilObjeto.getIntance().obtenerValorDefecto(pais, obtenerValorDefecto());
	}
	

	public UUID getId() {
		return id;
	}


	private void setId( final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}


	public String getNombre() {
		return nombre;
	}


	private void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
	}
	
}