<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodaj użytkownika</title>
</head>
<body>
	<div><%@ include file="/WEB-INF/views/fragments/header.jspf"%></div>
	<div>
	<form action="./addNewUser" method="post">
	<label>Podaj Imię i nazwisko<input type="text" name="name"/></label>
	<label>Podaj email<input type="email" name="email"/></label>
	<label>Podaj hasło<input type="password" name="password"/></label>
	<div><label> Grupa: <select name="userGroupId">
		 <c:forEach items="${groups }" var="group">
				<option value="${group.getId()}">${group.getName()}</option>		 
		 </c:forEach></select></label> </div>
		 <input type="submit" value="Dodaj użytkownika"/>
	</form>
	</div>

	<div><%@ include file="/WEB-INF/views/fragments/footer.jspf"%></div>

</body>
</html>