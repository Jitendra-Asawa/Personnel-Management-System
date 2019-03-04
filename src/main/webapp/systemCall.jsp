<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.io.*, java.util.*, dao.*, data.*, html.*, management.*,com.pms.*"%>
<jsp:declaration>String typeSystem = new String();
	String titleName = new String();</jsp:declaration>
<%
	String pageTitle = "System";
%>
<jsp:include page="htmlBodyOpen.jsp" flush="false">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<%=typeSystem = request.getParameter("ms")%>
<%
	if (typeSystem.equals("ems")) {
		titleName = "Employee Management System";
%>
<jsp:forward page="System">
	<jsp:param value="ems" name="ms" />
</jsp:forward>
<%
	} else {
		titleName = "Customer Management System";
%>
<jsp:forward page="System">
	<jsp:param value="cms" name="ms" />
</jsp:forward>
<%
	}
%>
<jsp:include page="bodyHtmlClose.jsp" />
