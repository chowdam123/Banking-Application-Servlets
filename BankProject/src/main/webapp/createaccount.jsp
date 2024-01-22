<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Your Account</title>
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
	<form action="CreateAccount" method="post">
		Name :<input type="text" name="name" ><br/>
		Father Name :<input type="text" name="fathername" ><br/>
		<label for="birthday"> </label>
		Date of Birth :<input type="date" id="birthday" name="birthday"><br/>
		<label for="gender">Gender :</label>

		<select name="gender" id="gender">
		  <option value="male">Male</option>
		  <option value="female">Female</option>
		</select><br/>
		Address :<input type="text" name="address" ><br/>
		Balance:<input type="text" name="balance" ><br/>
		Aadhar Number :<input type="text" name="aadharnumber"><br/>
		<input type="submit" value="Create Account">
		
	</form>
<body>

</body>
</html>