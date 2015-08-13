package tony.project.mvc.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tony.project.mvc.dao.CustomerDAO;
import tony.project.mvc.dao.impl.CustomerDAOJdbcImpl;
import tony.project.mvc.domain.Customer;

public class CustomerDAOJdbcImplTest {

	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
		
	
	@Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		System.out.println(customers);
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setAddress("ChongQing");
		customer.setName("Terry");
		customer.setPhone("157423");
		
		customerDAO.save(customer);
		
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(1);
		System.out.println(cust);
		
	}

	@Test
	public void testDelete() {

		customerDAO.delete(1);
	}

	@Test
	public void testGetCountWithName() {

		long count = customerDAO.getCountWithName("Terry");
		System.out.println(count);
	}

	@Test
	public void testDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
