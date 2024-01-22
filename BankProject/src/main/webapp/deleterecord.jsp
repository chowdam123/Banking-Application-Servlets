<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Account</title>
</head>
	<link rel="stylesheet" href="style.css">
<body>
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<form action="DeleteRecordServlet" method="post">
		<label for="accountname">Account holder name: </label>
		<input type="text" name="accountname"><br/>
		
		<button style="text-align:center;">Delete</button>
	</form>
	

</body>
</html>