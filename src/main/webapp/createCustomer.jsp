<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageTitle = "Create Customer Record";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<form>
	<input type="button" onclick="history.back()" value="Go Back" />
</form>
<h3 style="text-align: center">Fill in Customer details</h3>
<form action="CreateCustomer" method="post">
	ID: <input type="number" name="id" required pattern="[0-9]+">
	Name: <input type="text" name="name" required pattern="[a-zA-Z ]+">
	Amount: <input type="number" name="amount" pattern="[0-9]+">
	<button type="submit" value="ms">Submit</button>
</form>

<jsp:include page="bodyHtmlClose.jsp" />
