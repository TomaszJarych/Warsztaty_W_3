<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edytuj grupę</title>
</head>
<body>
	<div><%@ include file="../fragments/header.jspf"%></div>

	<div>
		<form action="./editGroup" method="post">
			<label>ID <input type="text" name="id"
				value="${group.getId() }" readonly>
			</label> <label>Podaj nazwę grupy <input type="text" name="name"
				value="${group.getName() }">
			</label> <input type="submit" value="Edytuj" />
		</form>
	</div>


	<div><%@ include file="../fragments/footer.jspf"%></div>


</body>
</html>