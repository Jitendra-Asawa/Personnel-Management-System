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



<%-- <jsp:include page="showAllRecords.jsp" /> --%>
<%
	ManagementCustEmp manObj = new ManagementCustEmp();
	List<CustEmp> list = (List<CustEmp>) manObj.readAll();
	manObj.closeConnection();
	request.setAttribute("list", list);
	RequestDispatcher rs = request.getRequestDispatcher("showAllRecords.jsp");
	rs.forward(request, response);
%>

<jsp:include page="bodyHtmlClose.jsp" />
