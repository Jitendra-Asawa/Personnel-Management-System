<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page
	import="html.*, com.pms.*,data.Customer,java.util.*,java.text.SimpleDateFormat,java.text.DateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%! @SuppressWarnings("unchecked")%> --%>


<jsp:declaration>String page = new String();</jsp:declaration>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.align-left {
	text-align: left;
	background-color: white;
	padding: 20px;
	margin-left: -10px;
	color: green;
}
</style>
<meta charset="UTF-8">
<title>CMS</title>
</head>
<%=HtmlUtility.homeButton()%>
<body style="text-align: center;">

	<form class="align-left" action="createCustomer.jsp">
		<input type="submit" onclick="createCustomer.jsp" value="Create">
	</form>
	<%
		List<Customer> list = (List<Customer>) request.getAttribute("list");
	%>
	<table style="width: 75%">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Amount</th>
			<th>Create Time</th>
			<th>Last Updated</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${list}" var="emp">
			<tr>
				<td><c:out value="${emp.id}"></c:out></td>
				<td><c:out value="${emp.name}"></c:out></td>
				<td><c:out value="${emp.amount}"></c:out></td>
				<td><c:out value="${emp.formatCreate}"></c:out></td>
				<td><c:out value="${emp.formatUpdate}"></c:out></td>
				<td><form action="UpdateDeleteCustomer" method="get">
						<select name="choice"><option value="${emp.id}update">Update</option>
							<option value="${emp.id}delete">Delete</option>
						</select><input type="submit" value="Submit">
					</form></td>

			</tr>
		</c:forEach>

	</table>
	<jsp:include page="bodyHtmlClose.jsp" />