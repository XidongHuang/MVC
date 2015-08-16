package tony.project.mvc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import tony.project.mvc.dao.factory.CustomerDAOFactory;




public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void init() throws ServletException {
		CustomerDAOFactory.getInstance().setType("jdbc");

		//Read switch.properties under class path
		InputStream in = 
				getServletContext().getResourceAsStream("/WEB-INF/classes/switch.properties");

		Properties properties = new Properties();
		
		try {
			properties.load(in);
			//Gain type attribute from switch.properties
			String type = properties.getProperty("type");
			//Put "type" in CustomerDAOFactory 
			CustomerDAOFactory.getInstance().setType(type);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
