<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	table{
		border: 1px solid #000;
		border-collapse;
		width: 350px;
		
	}
	
	th,td{
		border: 1px solid #000;
		border-collapse;
		text-align: center;
	}
</style>
</head>
<body>
		
	<div align="center">
		<hr width="50%" color="gray">
		<h3>BOARD1 테이블의 게시물 상세내역</h3>
		<hr width="50%" color="gray">
		<table>
			<c:set var="dto" value="${cont }"></c:set><!-- ??이거 위에있으면 뭐어..? -->
			<c:if test="${!empty dto }">
				<tr>
					<th>작성자</th>
					<td>${dto.getBoard_writer() }</td>
				</tr>
				<tr>
					<th>글제목</th>
					<td>${dto.getBoard_title() }</td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="30" readonly>${dto.getBoard_cont() }</textarea> </td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${dto.getBoard_hit() }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${dto.getBoard_regdate() }</td>
				</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2">
						<h3>검색된 레코드가 없습니당,</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>	
	
		
		
</body>
</html>