package co.edu.uco.onlinetest.dto;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public class DepartamentoDTO {


	private UUID id;
	private String nombre;
	private PaisDTO pais;
	
	public DepartamentoDTO() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setPais(PaisDTO.obtenerValorDefecto());
		
	}
	
	public DepartamentoDTO(final UUID id, final String nombre, final PaisDTO pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
		
	}

	public static DepartamentoDTO obtenerValorDefecto() {
		return new DepartamentoDTO();
	}
	
	public static DepartamentoDTO obtenerValorDefecto(final DepartamentoDTO departamento) {
		return UtilObjeto.getIntance().obtenerValorDefecto(departamento, obtenerValorDefecto());
	}
	
	
	public DepartamentoDTO(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setPais(PaisDTO.obtenerValorDefecto(pais));
	}
	

	public UUID getId() {
		return id;
	}


	public DepartamentoDTO setId( final UUID id) {
		this.id = UtilUUID.obtenerValorDefecto(id);
		return this;
	}


	public String getNombre() {
		return nombre;
	}


	public DepartamentoDTO setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
		return this;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public DepartamentoDTO setPais(final PaisDTO pais) {
		this.pais = PaisDTO.obtenerValorDefecto(pais);
		return this;
	}
	
}
