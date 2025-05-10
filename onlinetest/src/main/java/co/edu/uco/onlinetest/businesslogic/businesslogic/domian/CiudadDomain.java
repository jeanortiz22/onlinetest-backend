package co.edu.uco.onlinetest.businesslogic.businesslogic.domian;
import java.util.UUID;

import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.onlinetest.crosscutting.utilitarios.UtilUUID;

public class CiudadDomain {


	private UUID id;
	private String nombre;
	private DepartamentoDomain departamento;
	
	CiudadDomain() {
		setId(UtilUUID.obtenerValorDefecto());
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setDepartamento(DepartamentoDomain.obtenerValorDefecto());
		
	}
	
	public CiudadDomain(final UUID id) {
		setId(id);
		setNombre(UtilTexto.getInstance().obtenerValorDefecto());
		setDepartamento(DepartamentoDomain.obtenerValorDefecto(departamento));
	}
	
	
	public CiudadDomain(final UUID id, final String nombre, final DepartamentoDomain departamento) {
		setId(id);
		setNombre(nombre);
		setDepartamento(departamento);
		
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


	private void setNombre(final String nombre) {
		this.nombre = UtilTexto.getInstance().quitarEspacioBlancoInicioFin(nombre);
	}

	public DepartamentoDomain getDepartamento() {
		return departamento;
	}

	private void setDepartamento(final DepartamentoDomain departamento) {
		this.departamento = DepartamentoDomain.obtenerValorDefecto(departamento);
	}
	
	
}
