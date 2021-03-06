<%@page import="java.util.ArrayList"%>
<%@page import="tony.project.mvc.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="tony" uri="http://tony.com/myTag/core" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.3.1.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var content = $(this).parent().parent().find("td:eq(1)").text();
			var flag = confirm("Want to delete " + content +"'s information?");
			return flag;
			
		});
		
		
	});



</script>


</head>
<body>

	<form action="query.do" method="post">
		<table>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name"/></td>
		
			</tr>
			
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address"/></td>
		
			</tr>
			
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone"/></td>
		
			</tr>
			
			<tr>
				<td><input type="submit" value="Query" /></td>
				<td><a href="newcustomer.jsp">Add new Customer</td>
		
			</tr>
			
		</table>
	
	
	</form>
	
	<br><br>

	
	<%--
	List<Customer> customers = (List<Customer>)request.getAttribute("customer");	
	out.println("test1");
	out.println(customers == null);
	
	if(customers  != null && customers.size()>0) {
		out.println("test2");}
	--%>
	<c:if test="${!empty requestScope.customer }">
	
	<hr>
	<br><br>
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>CustomerName</th>
				<th>Address</th>
				<th>Phone</th>
				<th>Update\Delete</th>
			</tr>
	
	
	
	<c:forEach items="${requestScope.customer }" var="cust">
		<tr>
			<td>${cust.id }</td>
			<td>${cust.name }</td>
			<td>${cust.address }</td>
			<td>${cust.phone }</td>
			<%
				List<Customer> list = (List<Customer>)request.getAttribute("customer");
				System.out.println(list.get(0).getPhone());
			%>
			<td>
				<c:url value="/edit.do" var="editurl">
				
					<c:param name="id" value="${cust.id }"></c:param>
				</c:url>
				<a href="${editurl}">Update</a>
				
				<c:url value="/deleteCustomer.do" var="deleteurl">
					<c:param name="id" value="${cust.id }"></c:param>
				</c:url>
				
				<a href="${deleteurl }" class="delete">Delete</a>
			</td>
		</tr>
	
	</c:forEach>
		</table>
	
	
	</c:if>
	
	

</body>
</html>