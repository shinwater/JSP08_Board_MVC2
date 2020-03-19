package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardCheckOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		String pwd = request.getParameter("pwd");
		int cn = Integer.parseInt(request.getParameter("cn"));
		String path =null;
		
		BoardDAO dao = BoardDAO.getInstance();
		int res = dao.boardCheck(no,pwd);
		
		request.setAttribute("board_no", no);
		
		PrintWriter out = response.getWriter();
		if(res>0) {//비밀번호 확인 성공했을경우
			if(cn == 1) {
				path="board_update.do?no="+no;
			}else if(cn ==2) {
				path="board_delete.do?no="+no;
			}
		}else {//비밀번호 확인 실패했을 꼉우
			out.println("<script>");
			out.println("alert('비밀번호 틀림 ㅎㅎ')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
		
		
		
		return path;
	}

}
