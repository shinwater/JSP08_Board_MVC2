<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<form action="board_update_ok.do">
	<c:set var="list" value="${List }"/>
		<input type="hidden" name="board_no" value="${list.getBoard_no() }">
		
		<table>
			<tr>
				<th>글쓴이</th>
				<td><input name="board_writer" value="${list.getBoard_writer() }"></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td><input name="board_title" value="${list.getBoard_title() }"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="7" cols="30" name="board_cont">${list.getBoard_cont() }</textarea> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="board_pwd" value="${list.getBoard_pwd() }"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${list.getBoard_hit() }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${list.getBoard_regdate() }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기">
					<input type="reset" value="다시작성">
					<input type="button" value="뒤로가기" onclick="history.back()">
				</td>
			</tr>
		
		</table>
	
	</form>
	</div>
</body>
</html>