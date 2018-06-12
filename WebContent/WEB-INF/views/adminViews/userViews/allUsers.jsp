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
					<td align="center">Imię i nazwisko</td>
					<td align="center">E-mail</td>
					<td align="center">Akcja</td>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.getId()}</td>
						<td>${user.getUserName()}</td>
						<td>${user.getEmail()}</td>
						<td>
							<a href="./UserById?id=${user.getId()}"> Dane
								użytkownika</a>
							<a href=".//editUser?id=${user.getId()}"> Edytuj użytkownika</a>
							<a href="./deleteUser?id=${user.getId()}"> Usuń użytkownika</a>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div><h1><a href="./addNewUser">Dodaj użytkownika</a></h1></div>

	<div><%@ include file="/WEB-INF/views/fragments/footer.jspf"%></div>


</body>
</html>