package tony.project.mvc.dao;

import java.util.List;

import tony.project.mvc.domain.Customer;

public interface CustomerDAO {

	
	public List<Customer> getAll();
	
	public void save(Customer customer);
	
	public Customer get(Integer id);
	
	public void delete(Integer id);
	
	/**
	 * return numbers of record that corresponds with a customer name
	 * 
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
	
}
