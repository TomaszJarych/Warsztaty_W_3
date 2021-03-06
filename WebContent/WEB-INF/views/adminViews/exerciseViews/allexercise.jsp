<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/fragments/header.jspf"%>
	</div>
	<div>
		<table align="left" border="1" bgcolor="silver">
			<thead>
				<tr>
					<td align="center">ID</td>
					<td align="center">Tytuł</td>
					<td align="center">Opis</td>
					<td align="center">Akcja</td>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${exercise}" var="exercis">
					<tr>
						<td>${exercis.getId()}</td>
						<td>${exercis.getTitle()}</td>
						<td>${exercis.getDescription()}</td>
						<td> <a href="./editExercise?id=${exercis.getId()}"> Edytuj
								zadanie</a> <a href="./deleteExercise?id=${exercis.getId()}"> Usuń
								zadanie</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<h1>
			<a href="./addNewExercise">Dodaj zadanie</a>
		</h1>
	</div>

	<div><%@ include file="/WEB-INF/views/fragments/footer.jspf"%></div>


</body>
</html>