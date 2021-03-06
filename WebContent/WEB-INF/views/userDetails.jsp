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
	<div>
		<%@ include file="./fragments/header.jspf"%>
	</div>
	<div>
		<h1>ID: ${user.getId()}</h1>
		<h1>Imię i nazwisko: ${user.getUserName()}</h1>
		<h1>E-mail: ${user.getEmail() }</h1>
		<h1>Grupa: ${group.getName() }</h1>
	</div>
	<div>
		<table align="left" border="1" bgcolor="silver">
			<thead>
				<tr>
					<td>ID</td>
					<td>Created</td>
					<td>Updated</td>
					<td>Description</td>
					<td>ExerciseId</td>
					<td>Szczegóły</td>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${solutions }" var="solution">
					<tr>
						<td>${solution.getId()}</td>
						<td>${solution.getCreated()}</td>
						<td>${solution.getUpdated()}</td>
						<td>${solution.getDescription()}</td>
						<td>${solution.getExercise_id()}</td>
						<td><a href="./solutionById?id=${solution.getId()}">
								Szczegóły rozwiązania</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<div>
		<%@ include file="./fragments/footer.jspf"%>
	</div>

</body>
</html>