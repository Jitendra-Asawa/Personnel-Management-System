<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%
	String pageTitle = "View History";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<body style="text-align: center">
	<form style="text-align:left">
		<input type="button" onclick="history.back()" value="Go Back" />
	</form>
	<%
		List<List<String>> list = (List<List<String>>) request.getAttribute("list");
	%>
	<h3>Users on System</h3>
	<table style="text-align: center">
		<tr>
			<th>Serial No.</th>
			<th>ID</th>
			<th>Name</th>
			<th>Type</th>
		</tr>
		<c:forEach var="innerList" items="${list}">
			<tr>
				<c:forEach var="obj" items="${innerList}">
					<td><c:out value="${obj}"></c:out></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>