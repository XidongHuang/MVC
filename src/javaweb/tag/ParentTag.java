package javaweb.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ParentTag extends SimpleTagSupport {
	
	private String name = "Tony";
	
	public String getName() {
		return name;
	}
	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("ParentTag: name -- " + name );
		getJspBody().invoke(null);

		
	
	}
}
