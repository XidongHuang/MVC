package tony.project.mvc.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Operating tools for JDBC
 * 
 * 
 *
 */
public class JdbcUtils {
	
	/**
	 * release connection
	 * @param connection
	 */
	public static void releaseConnection(Connection connection){
		
		try {
			
			if(connection != null){
				connection.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	}
	
	
	private static DataSource dataSource = null;
	
	static{
		
		//data source just only be created once (for saving resources)
		dataSource = new ComboPooledDataSource("mvcapp");
		
	}
	
	
	/**
	 * return a database connection instance
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnetion() throws SQLException{
		
		return dataSource.getConnection();
	}
	
	
}
