<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solution details</title>
</head>
<body>
	<div>
		<%@ include file="./fragments/header.jspf"%>
	</div>
	<h1>ID rozwiązania:${solution.getId()}</h1>
	<h1>Autor: :${user.getUserName()}</h1>
	<h1>E-mail: ${user.getEmail() }</h1>
	<h1>Stworzono: ${solution.getCreated()}</h1>
	<h1>Aktualizacja: ${solution.getUpdated()}</h1>
	<h1>Rozwiązanie: ${solution.getDescription()}</h1>
	<h1>ID zadania: ${solution.getExercise_id()}</h1>
	<div>
		<%@ include file="./fragments/footer.jspf"%>
	</div>

</body>
</html>