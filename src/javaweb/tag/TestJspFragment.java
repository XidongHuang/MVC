package javaweb.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TestJspFragment extends SimpleTagSupport{
	
	
	@Override
	public void doTag() throws JspException, IOException {

		JspFragment bodyContent = getJspBody();
		//JspFragment.invoke(Writer): Writer is character stream output of information tag body
		//if it null, then out put to getJspContext().getOut() --- to page
		
		//1. Gain information of tag body by StringWriter
		StringWriter sw = new StringWriter();
		bodyContent.invoke(sw);
		//getJspContext().getOut().print("---");
		
		//2. Change all letters of tag body into capital 
		String content = sw.toString().toUpperCase();
		System.out.println(content);
		
		
		//3. Gain out object from JSP page, then print out on page
		getJspContext().getOut().print(content);
		
		
	
	}
}
