<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Withdraw Amount</title>
</head>
<link rel="stylesheet" href="style.css">
<body>
	
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<h2>Money Withdraw</h2>
	<form action="WithdrawAmount" method="post">
		<label for="accountname">Account holder name: </label>
		<input type="text" name="accountname"><br/>
		<label for="withdraw">Money to be Withdraw: </label>
		<input type="text" name="withdraw"><br/>
		<button style="text-align:center;">Withdraw</button>
	</form>
	

</body>
</html>