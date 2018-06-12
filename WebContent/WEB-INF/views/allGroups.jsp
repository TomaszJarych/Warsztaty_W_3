<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Grupy</title>
</head>
<body>
	<%@ include file="./fragments/header.jspf"%>
	</div>
	<div>
		<table align="left" border="1" bgcolor="silver">
			<thead>
				<tr>
					<td>ID</td>
					<td>Nazwa</td>

				</tr>
			</thead>
			<tbody>

				<c:forEach items="${groups}" var="group">
					<tr>
						<td>${group.getId()}</td>
						<td>${group.getName()}</td>
						<td><a href="./usersByGroup?id=${group.getId()}">
								Lista uczestników</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div><%@ include file="./fragments/footer.jspf"%></div>

</body>
</html>