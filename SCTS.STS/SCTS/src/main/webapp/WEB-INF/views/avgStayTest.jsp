<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>MAJOR</th>
			<th>MINOR</th>
			<th>AVGSTAY</th>
			<th>COUNT</th>
		</tr>
		
		<c:forEach items="${ list }" var="vo">
		
			<tr>
				<td>${ vo.get("major") }</td>
				<td>${ vo.get("minor") }</a></td>
				<td>${ vo.get("avg") }</td>
				<td>${ vo.get("cnt") }</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
