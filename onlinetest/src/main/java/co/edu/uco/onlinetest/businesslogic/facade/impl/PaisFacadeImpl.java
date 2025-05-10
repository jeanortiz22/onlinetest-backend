package co.edu.uco.onlinetest.businesslogic.facade.impl;

import co.edu.uco.onlinetest.businesslogic.businesslogic.impl.PaisBusinessLogicImpl;
import co.edu.uco.onlinetest.data.dao.factory.DAOFactory;
import co.edu.uco.onlinetest.data.dao.factory.Factory;

public class PaisFacadeImpl  implements PaisFacade{
	
	private DAOFactory daoFactory;
	private PaisBusinessLogic paisBusinessLogic;
	
	public PaisFacadeImpl() {
		daoFactory = DAOFactory.getFactory(Factory.AZURE_SQL);
		paisBusinessLogic = new PaisBusinessLogicImpl(daoFactory);
	}

}
