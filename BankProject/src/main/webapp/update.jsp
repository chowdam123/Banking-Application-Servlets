<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Account Information</title>
</head>
	<link rel="stylesheet" href="style.css">
<body>
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<h1>Update  Account Information</h1>
	<form action="UpdateNameServlet" method="post">
		<label for="accountname">Account holder name: </label>
		<input type="text" name="accountname"><br/>
		<label for="newname">New Name: </label>
		<input type="text" name="newname"><br/>
		<button style="text-align:center;">Update</button>
	</form>

</body>
</html>