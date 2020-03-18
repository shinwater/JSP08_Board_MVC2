<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<hr width="50%" color="red">
		<h3>BOARD1 테이블의 글쓰기 폼</h3>
		<hr width="50%" color="red">
		<form method="post" action="<%=request.getContextPath() %>/board_write_ok.do">
			<table border="0">
				<tr>
					<th>작성자</th>
					<td><input name="writer"></td>
				</tr>
				<tr>
					<th>글제목</th>
					<td><input name="title"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea rows="7" cols="30" name="content"></textarea> </td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글ㅆ크기"/>&ensp; 
						<input type="reset" value="취송"/>&ensp; 
					</td>
				</tr>

			</table>
					
		
		
		</form>
		
	</div>
</body>
</html>