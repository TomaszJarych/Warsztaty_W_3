<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div><%@ include file="/WEB-INF/views/fragments/header.jspf"%></div>
	<form action="./editExercise" method="post">
		<label>ID: <input type="number" name="id" readonly value="${exercise.getId()}"></label>
		<label>Nazwa zadania: <input type="text" name="name" value="${exercise.getTitle()}"></label>
		<label>Opis zadania: <input type="text" name="description" value="${exercise.getDescription()}"></label>
		<input type="submit" value="Dodaj zadanie">
	</form>

	<div><%@ include file="/WEB-INF/views/fragments/footer.jspf"%></div>



</body>
</html>