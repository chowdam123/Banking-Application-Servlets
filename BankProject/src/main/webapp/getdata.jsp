<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
</head>
	<style>
		body{
			text-align:center;
		}
	</style>
	<link rel="stylesheet" href="style.css">
<body>
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<form action="GetData" method="post">
	<h2>Welcome <%= session.getAttribute("username") %>!</h2>
	Account holder name:      <input type="text" name="name" required ><br/>

	<button style="background-color:green">Display</button>
	
	 
	</form>
</body>
</html>