package tony.project.mvc.dao;

import java.util.List;

import tony.project.mvc.domain.Customer;

public interface CustomerDAO {

	/**
	 * return the List that satisfy with searching requirements
	 * 
	 * @param cc Encapsulate searching request
	 * @return
	 */
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc);
	
	public List<Customer> getAll();
	
	public void save(Customer customer);
	
	public Customer get(Integer id);
	
	public void delete(Integer id);
	
	public void update(Customer customer);
	/**
	 * return numbers of record that corresponds with a customer name
	 * 
	 * @param name
	 * @return
	 */
	public long getCountWithName(String name);
	
}
