<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/login" var="linkServletNovaEmpresa"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

	<form action="${linkServletLogin }" method="post">
	
		Usuario: <input type="text" name="login"  />
		Senha: <input type="password" name="senha"  />
	
		<input type="submit" />
	</form>

</body>
</html>