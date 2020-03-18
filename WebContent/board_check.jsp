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
		<c:set var="board_no" value="${no }" />
		<c:set var="cn" value="${cn }" />
		<form action="board_check_ok.do">
			<input type="hidden" name="no" value="${board_no }"> <input
				type="hidden" name="cn" value="${cn }">
			<table>


				<tr>

					<th>비밀번호 확인 :</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="확인"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>