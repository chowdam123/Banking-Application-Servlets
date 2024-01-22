<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>
	<h2>Logout successful</h2>
	<p>You have been successfully logged out, <%=session.getAttribute("username") %>!</p>
	<% HttpSession mySession=request.getSession(false);
		if(mySession!=null)
		{
			mySession.invalidate();
		}
	%>
	<jsp:include page="Login.jsp"/>
	</body>
</html>