<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="data.*"%>
<%
	String pageTitle = "Update Customer Record";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="UTF-8"> -->
<!-- <title>Update Record</title> -->
<!-- </head> -->
<!-- <body> -->
<form style="text-align: left;" action="redirectToSystem.jsp">
	<button type="submit" onclick="System">Go Back</button>
</form>
<%
	Customer custObj = (Customer) request.getAttribute("custObj");
%>
<h3 style="text-align: center">Update Customer Details</h3>
<form action="UpdateDeleteCustomer" method="post">
	ID: <input type="number" name="id" readonly pattern="[0-9]+"
		value="${custObj.id }"> Name: <input type="text" name="name"
		pattern="[a-zA-Z ]+" value="${custObj.getName() }"> Amount: <input
		type="number" name="amount" min="0" pattern="[0-9]+"
		value="${custObj.amount }">
	<button type="submit">Submit</button>
</form>
<jsp:include page="bodyHtmlClose.jsp" />
