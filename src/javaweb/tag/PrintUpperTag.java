package javaweb.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class PrintUpperTag extends SimpleTagSupport {

	private String time = null;
	
	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		int count = 0;
		//Gain content information
		JspFragment bodyContent = getJspBody();
	
		StringWriter sw = new StringWriter();
		
		bodyContent.invoke(sw);
		
		String upperS = sw.toString().toUpperCase();
		//2. Translate the information into capital
		
		
		try {
			
			count = Integer.parseInt(time);
		} catch (Exception e) {
			getJspContext().getOut().print("Invalie input");
		}
		
		if(count >0){
		for(int i = 0; i<count;i++){
			getJspContext().getOut().print(i+1+"."+upperS+"<br>");
		}
		
		}
		
		
	}
	
	
}
