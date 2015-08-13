package tony.project.mvc.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import tony.project.mvc.db.JdbcUtils;

public class jdbcUtilsTest {

	@Test
	public void testGetConnetion() throws SQLException {
		Connection connection = JdbcUtils.getConnetion();
		
		System.out.println(connection);
	}

}
