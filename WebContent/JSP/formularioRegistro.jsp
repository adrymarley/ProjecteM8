<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTRARSE</title>
</head>
<body>
	<h1>FORMULARI REGISTRE</h1>
	<form action="Insert" method="POST">
		<table>
			<tr>
				<td>User</td>
				<td><input name="user" /></td>
			</tr>
			<tr>
				<td>password</td>
				<td><input name="password" /></td>
			</tr>
			<tr>
				<td>email</td>
				<td><input name="email" /></td>
			</tr>
		</table>
		<input type="submit" />
	</form>
	<br>
	<a href="#"><button>Volver</button></a>
</body>
</html>