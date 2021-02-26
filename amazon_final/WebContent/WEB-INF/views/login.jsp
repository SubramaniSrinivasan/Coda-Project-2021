<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Amazon </title>
</head>
<body>
	<spring:form commandName="formBeanObject" method="POST" action="login">
	
		UserName : <spring:input type="text" path="uname"/>
		</br>
		PassWord : <spring:input type="password" path="upass"/>
		</br>
		<input type="submit" value="login">
		
	</spring:form>
</body>
</html>