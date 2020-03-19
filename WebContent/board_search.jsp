<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="50%" color="gray">
		<h3>검색 결과</h3>
		<hr width="50%" color="gray">
		<table>
			<c:set var="list" value="${List }" />
			<c:if test="${!empty list }">


				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list }" var="dto">


					<tr>

						<td>${dto.getBoard_no() }</td>


						<td>${dto.getBoard_title() }</td>


						<td>${dto.getBoard_hit() }</td>

						<td>${dto.getBoard_regdate() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr>
					<td>검색결과가 없습니다.</td>
				</tr>
			</c:if>
		</table>
	</div>
</body>
</html>