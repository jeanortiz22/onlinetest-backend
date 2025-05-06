package co.edu.uco.onlinetest.dto;

import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public final class PaisDTO {

	private UUID id;
	private String nombre;
	
	public PaisDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		
	}
	
	public PaisDTO(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
	}
	
	public PaisDTO(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
		
	}
	
	private PaisDTO (final Builder builder) {
		setId(builder.id);
		setNombre(builder.nombre);
	}
	
	public static PaisDTO obtenerValorDefecto() {
		return new PaisDTO();
	}
	
	public static PaisDTO obtenerValorDefecto(final PaisDTO pais) {
		return UtilObjeto.getIntance().obtenerValorDefecto(pais, obtenerValorDefecto());
	}
	

	public UUID getId() {
		return id;
	}


	public void setId( final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
	}
	
	public static class Builder {
		
		private UUID id;
		private String nombre;
		
		public Builder id (final UUID id) {
			this.id = id;
			return this;
		}
		
		public Builder nombre (final String nombre) {
			this.nombre = nombre;
			return this;
		}
		
		public PaisDTO crear() {
			return new PaisDTO(this);
		}
	}
}