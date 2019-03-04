<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page
	import="management.ManagementCustEmp, data.*,com.pms.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String pageTitle = "Choose System";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<p style="text-align: center;">Select the type of Management System</p>
<form action="systemCall.jsp">
	<input type="radio" name="ms" value="ems" checked>Employee
	Management System <input type="radio" name="ms" value="cms" onclick="">Customer
	Management System <input type="submit" value="Submit">
</form>
<p />
<form action="ViewHistory" method="get">
	<input type="submit" value="View History">
</form>
<p />
<%
	List<CustEmp> list = (List<CustEmp>) request.getAttribute("list");
%>
<table style="text-align: center; width: 75%;">

	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Amount</th>
		<th>Team</th>
		<th>Create Time</th>
		<th>Last Updated</th>
		<th>Type</th>
	</tr>
	<c:forEach items="${list}" var="record">
		<tr>
			<td><c:out value="${record.id}"></c:out></td>
			<td><c:out value="${record.name}"></c:out></td>
			<td><c:out value="${record.amount}"></c:out></td>
			<td><c:out value="${record.team}"></c:out></td>
			<td><c:out value="${record.formatCreate}"></c:out></td>
			<td><c:out value="${record.formatUpdate}"></c:out></td>
			<td><c:out value="${record.className}"></c:out></td>
		</tr>
	</c:forEach>
</table>