<%@page import="com.model.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Shop"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title> Shop 3 </title>
	</head>
	<body>
		<%@ include file="logout.jsp" %>
		<h3>Welcome <%= session.getAttribute("uname") %></h3>
		<% Shop shop = (Shop)request.getAttribute("shop");
		   List<Item> list = (List<Item>)request.getAttribute("items");
		%>
		<h3> Shop Name : <%= shop.getShopName() %> </h3>
		
		<form action="showshop" method="POST">
			<input type="hidden" name="shopid" value="invoice">
			<%  for(Item item : list){ %>
				<img src="<%= item.getImageURL() %>" alt="<%= item.getItemDescription() %>" width="200" height="200">
				<br>
				<input type="checkbox" name="<%= item.getItemID() %>" value="<%= item.getItemDescription() %>"><%= item.getItemDescription() %>
				<br>
			<% } %>
			<input type="submit" value="Shop Cart">
		</form>
		
	</body>
</html>