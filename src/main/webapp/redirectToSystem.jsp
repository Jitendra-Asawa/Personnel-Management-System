
<%
	String pageTitle = "Redirecting To System";
%>
<jsp:include page="htmlBodyOpen.jsp">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<jsp:forward page="System">
	<jsp:param value="cms" name="ms" />
</jsp:forward>

<jsp:include page="bodyHtmlClose.jsp" />
