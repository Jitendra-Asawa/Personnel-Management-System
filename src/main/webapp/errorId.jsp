<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String pageTitle = "Record Exists!!";
%>
<jsp:include page="htmlBodyOpen.jsp" flush="false">
	<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<form>
	<input type="button" onclick="history.back()" value="Go Back"/>
</form>
<h3 style="text-align: center">Record Already Exists!!<br>Create record with non-existing unique ID</h3>
</body>
</html>