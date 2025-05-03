package co.edu.uco.onlinetest.entity;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public class DepartamentoEntity {


	private UUID id;
	private String nombre;
	private PaisEntity pais;
	
	public DepartamentoEntity() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setPais(new PaisEntity());
		
	}
	
	public DepartamentoEntity(final UUID id, final String nombre, final PaisEntity pais) {
		setId(id);
		setNombre(nombre);
		setPais(pais);
		
	}
	public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity departamento) {
		return UtilObjeto.getIntance().obtenerValorDefecto(departamento, new DepartamentoEntity());
	}
	
	
	public DepartamentoEntity(final UUID id) {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setPais(PaisEntity.obtenerValorDefecto(pais));
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

	public PaisEntity getPais() {
		return pais;
	}

	public void setPais(final PaisEntity pais) {
		this.pais = PaisEntity.obtenerValorDefecto(pais);
	}
	
	
}
