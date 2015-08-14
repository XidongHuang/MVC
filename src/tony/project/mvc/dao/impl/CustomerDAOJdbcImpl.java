package tony.project.mvc.dao.impl;

import java.util.List;

import tony.project.mvc.dao.CriteriaCustomer;
import tony.project.mvc.dao.CustomerDAO;
import tony.project.mvc.dao.DAO;
import tony.project.mvc.domain.Customer;

public class CustomerDAOJdbcImpl extends DAO<Customer> implements CustomerDAO {

	@Override
	public List<Customer> getAll() {
		
		String sql = "SELECT * FROM customer";
		
		return getForList(sql);
	}

	@Override
	public void save(Customer customer) {
		String sql = "INSERT INTO customer(name, address, phone) VALUES(?,?,?)";
		update(sql, customer.getName(),customer.getAddress(),customer.getPhone());
		
	}

	@Override
	public Customer get(Integer id) {
		String sql = "SELECT id, name, address, phone FROM customer WHERE id = ?";
		

		return get(sql, id);
	}

	@Override
	public void delete(Integer id) {

		String sql = "DELETE FROM customer WHERE id = ?";
		update(sql, id);
	}

	@Override
	public long getCountWithName(String name) {
		String sql = "SELECT COUNT(id) FROM customer WHERE name = ?";
		
		return getForValue(sql, name);
	}

	@Override
	public List<Customer> getForListWithCriteriaCustomer(CriteriaCustomer cc) {
		
		String sql = "SELECT id, name, address, phone FROM customer WHERE "
				+ "name LIKE ? AND address LIKE ? AND phone LIKE ?";
		
		
		// change CriteriaCustomer's get() Methods to gain returning String with "%%"
		// if returning value is null, return "%%"; if not null, return "%" + string +"%"
		return getForList(sql, cc.getName(),cc.getAddress(),cc.getPhone());
	}

}
