package co.edu.uco.onlinetest.data.dao.entity.pais.impl.azuresql;

import java.sql.Connection;

import co.edu.uco.onlinetest.data.dao.entity.pais.PaisDAO;

public class PaisAzureSQLDAO implements PaisDAO{

	private Connection conexion;
	
	public PaisAzureSQLDAO (Connection conexion) {
		this.conexion = conexion;
	}
}
