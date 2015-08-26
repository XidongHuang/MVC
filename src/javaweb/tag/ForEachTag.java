package javaweb.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tony.project.mvc.domain.Customer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;



public class ForEachTag extends SimpleTagSupport {

	private Collection<Customer> items;
	private String var;
	
	public void setItems(Collection<Customer> items) {
		this.items = items;
	}
	
	public void setVar(String var) {
		this.var = var;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
//		JspFragment bodyContent = getJspBody();
//		items = (ArrayList<Customer>)getJspContext().getAttribute("customers");
//		var = (String)getJspContext().getAttribute("var");
		
		//
		if(items != null){
			for(Object obj:items){
				getJspContext().setAttribute(var, obj);
				getJspBody().invoke(null);
				
			}
			
		}
		
	
	}
}
