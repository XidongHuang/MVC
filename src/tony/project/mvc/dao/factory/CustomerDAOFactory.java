package tony.project.mvc.dao.factory;

import java.util.HashMap;
import java.util.Map;

import tony.project.mvc.dao.CustomerDAO;
import tony.project.mvc.dao.impl.CustomerDAOJdbcImpl;
import tony.project.mvc.dao.impl.CustomerDAOXMLImpl;

public class CustomerDAOFactory {
	
	
	private Map<String , CustomerDAO> daos = new HashMap<String, CustomerDAO>();

	private CustomerDAOFactory(){
		
		daos.put("jdbc", new CustomerDAOJdbcImpl());
		daos.put("xml", new CustomerDAOXMLImpl());
		
	}
	
	private static CustomerDAOFactory instance = new CustomerDAOFactory();
	
	
	public static CustomerDAOFactory getInstance(){
		
		return instance;
		
	}
	
	private static String type = null;
	
	public static void setType(String type) {
		CustomerDAOFactory.type = type;
	}
	
	public CustomerDAO getCustomerDAO(){
		
		return daos.get(type);
	}
	
}
