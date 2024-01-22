<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>login page </title>
</head>
	<style>
		body{
			text-align:center;
		}
	</style>
<body>
	<div>
		<img src="images/bank2.jpeg" alt="bank image2" width="900" height="350">
		<h2>Login</h2>
		<form action="LoginServlet" method="post">
			<input type="text" name="username" placeholder="username" required><br/>
			<input type="text" name="password" placeholder="password" required><br/>
			<input type="submit" value="Login">
			
		</form>
		<p>Not registered yet? <a href="Register.jsp">Register</a></p> 
		
	</div>

</body>
</html>