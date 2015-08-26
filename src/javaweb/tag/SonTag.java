package javaweb.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SonTag extends SimpleTagSupport {

	
	@Override
	public void doTag() throws JspException, IOException {
		//1. Gain reference from Parent Tag
		JspTag parent = getParent();
		
		//2. Gain name attribute from name Tag
		ParentTag parentTag = (ParentTag)parent;
		String name = parentTag.getName();
		
		//3. Print name on JSP page
		getJspContext().getOut().print("Son Tag prints: " + name);
	
	}
}
