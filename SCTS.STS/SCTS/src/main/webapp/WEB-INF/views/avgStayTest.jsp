<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>타일이름</th>
			<th>메이저</th>
			<th>마이너</th>
			<th>평균머문시간</th>
			<th>방문횟수</th>
		</tr>
		
		<c:forEach items="${ list }" var="vo">
		
			<tr>
				<td>${ vo.get("tile_name") }</td>
				<td>${ vo.get("major") }</td>
				<td>${ vo.get("minor") }</td>
				<td>${ vo.get("avg") }</td>
				<td>${ vo.get("cnt") }</td>
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
