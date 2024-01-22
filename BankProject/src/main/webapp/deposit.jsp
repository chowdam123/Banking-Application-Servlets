<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="style.css">
<body>
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<h2>Money deposit</h2>
	<form action="DepositMoney" method="post">
		<label for="accountname">Account holder name: </label>
		<input type="text" name="accountname"><br/>
		<label for="deposit">Money to be Deposited: </label>
		<input type="text" name="deposit"><br/>
		<button style="text-align:center;">deposit</button>
	</form>

</body>
</html>