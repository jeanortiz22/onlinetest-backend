package co.edu.uco.onlinetest.data.dao.factory;

import co.edu.uco.onlinetest.data.dao.factory.azuresql.AzureSQLDAOFactory;

public abstract class DAOFactory<DepartamentoDAO>{
	
	public static DAOFactory getFactory (Factory factory) {
		
		switch (factory) {
		case AZURE_SQL: {
			
			return new AzureSQLDAOFactory();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + factory);
		}
	}
	
	protected abstract void abrirConexion();
	
	public abstract void iniciarTrasaccion();
	
	public abstract void confirmarTransacion();

	public abstract void cancelarTransacion();

    public abstract PaisDAO getPaisDAO();
    
    public abstract DepartamentoDAO getDepartamentoDAO();
    
    public abstract CiudadDAO getCiudadDAO();

} 
