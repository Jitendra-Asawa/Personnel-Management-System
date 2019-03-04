<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageTitle = "Delete All Records";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<h3 style="text-align: center;">Delete All Records</h3>
<form action="DeleteAllCustomer" style="text-align: center;">
	<button type="submit" name="button1" value="yes"
		onclick="alert('All Records Deleted')">Yes</button>
	<button type="submit" name="button1" value="no"
		onclick="alert('Redirecting to CMS')">No</button>
</form>
<jsp:include page="bodyHtmlClose.jsp" />
