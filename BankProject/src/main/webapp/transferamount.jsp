<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>transactions</title>
</head>
	<link rel="stylesheet" href="style.css">
<body>
	<%RequestDispatcher rd = request.getRequestDispatcher("/User.jsp");
	rd.include(request, response); %>
	<h1>Transactions</h1>
	<form action="TransferAmountServlet" method="post">
		<label for="accountname">Account holder name: </label>
		<input type="text" name="accountname"><br/>
		<label for="recipient">Name of Recipient: </label>
		<input type="text" name="recipient"><br/>
		<label for="amount">Enter Amount To Transfer </label>
		<input type="text" name="amount"><br/>
		<button style="text-align:center;">Transfer</button>
	</form>
</body>
</html>