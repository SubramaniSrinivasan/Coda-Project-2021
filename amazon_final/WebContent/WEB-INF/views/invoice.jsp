<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Invoice </title>
</head>
<body>
	<%@ include file="logout.jsp" %>
	<h1> Invoice </h1>
	<h2>Welcome! Now Make the payment Mr/Ms..:<%= session.getAttribute("uname") %></h2>
		<%
			Enumeration<String> en=session.getAttributeNames();
			while(en.hasMoreElements()){
				String name=en.nextElement();
				try { 
					String value=(String)session.getAttribute(name);
					if(!name.equals("formid")&&!name.equals("shopid")&&!name.equals("uname")){
						out.println("<h1>"+name+":"+value+"</h1>");
					}
				}catch(Exception e) {
					
				}
			}
			
		%>
		
		<form action="payment" method="POST">
			<input type="submit" value="Make Payment">
		</form>
	
	</body>
</html>