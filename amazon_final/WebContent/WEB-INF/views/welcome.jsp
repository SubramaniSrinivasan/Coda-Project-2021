<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ include file="logout.jsp" %>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title> Welcome </title>
	</head>
	<body>
		<h3> Welcome <%= session.getAttribute("uname") %> </h3>
		<h1> Welcome To Amazon Shopping Site </h1>
		<form action="showshop" method="POST">
			<input type="hidden" name="shopid" value="s1">
			<input type="submit" value="Start Shopping">
		</form>
	</body>
</html>