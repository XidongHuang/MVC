package tony.project.mvc.servlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.el.MethodNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

import tony.project.mvc.dao.CriteriaCustomer;
import tony.project.mvc.dao.CustomerDAO;
import tony.project.mvc.dao.impl.CustomerDAOJdbcImpl;
import tony.project.mvc.domain.Customer;

@WebServlet("*.do")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CustomerDAO customerDAO = new CustomerDAOJdbcImpl();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String method = request.getParameter("method");
//
//		switch (method) {
//		case "add":
//			add(request, response);
//			break;
//		case "query":
//			query(request, response);
//			break;
//		case "delete":
//			delete(request, response);
//			break;
//		case "update":
//			update(request, response);
//			break;
//		}
//
//	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//1. Gain servletPath: /edit.do or /addCustomer.do
		String servletPath = req.getServletPath();
		
		//2. Get rid of / and .do to gain such edit or addCustomer string
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length()-3);
		
		Method method = null;
		try {
			//3. Use reflection to get the method that in the servlet
			method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
		
			
			//4. Invoke corresponding method by using reflection
			method.invoke(this,req, resp);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			// improve user experience  
			resp.sendRedirect("error.jsp");
		}
	}
	
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. Gain parameters from request
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		//2. Check name is existed or not
		//2.1 Invoke CustomerDAO's getCountWithName(String name) to check the name exists in database or not
		long same = customerDAO.getCountWithName(name);
		
		
		//2.2 If return value larger than 0, then response newcustomer.jsp page: 
		if(same > 0){
			
			//forward method to go back to newcustomer.jsp
			
			//2.2.1 show an error information on newcustomer.jsp page: "user name" exists already, please entry again!
			//put an attribute (message) in request: "user name" exists already, please entry again!
			//show on the page by request.getAttribute("message")
			request.setAttribute("message", name+" already exisits, please entry again.");

			//2.2.2 newcustomer.jsp page can be review
			//review through method: value="<%= request.getParameter("name")==null?"": request.getParameter("name")%>
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
		
			//2.2.3 if have same name End method: return 
		} else {
			//3. If it doesn't have same name
			//then Encapsulate parameters into a Customer instance
			Customer customer = new Customer( name, address, phone);
			
			//4. invoke CustomerDAO's save(Customer customer) to save operation
			customerDAO.save(customer);
			
			//5. redirect to success.jsp page
			// Using redirecting to avoid the problem of re-submit information
			response.sendRedirect("success.jsp");
		}

		
		
		
		
		
		
		
		
		System.out.println(request.getParameter("name"));
		System.out.println("add");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//Gain request's values
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		//Encapsulate values into CriteriaCustomer instance
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		
		//1. invoke CustomerDAO's getAll() method, to get the collection of Customer
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		
		//2. put the collection of customer into request
		request.setAttribute("customer", customers);
		
		//3. forward to index.jsp (same request, cannot be redirector)
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		System.out.println("query22");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		int id = 0;
		System.out.println(idStr);
		
		//try ... catch exception is for: prevent idStr cannot cast to int type
		//if cannot be casted, id will be 0, then cannot do any kind operation 
		try {
			id = Integer.parseInt(idStr);
			customerDAO.delete(id);
			
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("query.jsp");
		}
		response.sendRedirect("query.do");
		System.out.println("delete");
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit");
	}
	

}
