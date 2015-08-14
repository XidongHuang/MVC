package tony.project.mvc.servlet;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.el.MethodNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;

@WebServlet("*.do")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		methodName = servletPath.substring(1, methodName.length()-2);
		
		Method method = null;
		try {
			//3. Use reflection to get the method that in the servlet
			method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
		
			
			//4. Invoke corresponding method by using reflection
			method.invoke(this,req, resp);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			// imporve user experience  
			resp.sendRedirect("error.jsp");
		}
	}
	
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("add");
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("query");
	}

	private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete");
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit");
	}
	

}
