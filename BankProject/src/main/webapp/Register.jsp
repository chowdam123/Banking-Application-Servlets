<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
	<style>
		body{
			text-align:center;
		}
	</style>
	<script>
		function validate()
		{
			var pwd=document.register.password.value;
			var cnfw=document.register.confirmPassword.value;
			if(pwd!=cnfw)
			{
				alert("Password not Match with Confirm Password");
				return false;	
			}
		}
	</script>
<body>
	<div>
		<img src="images/bank1.jpeg" alt="bank image1" width="900" height="350">
		<h2>Register</h2>
		<form action="RegisterServlet" method="post" onsubmit="return validate()">
			<input type="text" name="username" placeholder="Username" required><br/>
			<input type="text" name="password" placeholder="Password" required><br/>
			<input type="text" name="confirmPassword" placeholder="Confirm Password" required><br/>
			<input type="submit" value="Register">
		
		</form>
		<p> Already have an account?<a href="Login.jsp">Login</a></p>
	
	</div>
	

</body>
</html>