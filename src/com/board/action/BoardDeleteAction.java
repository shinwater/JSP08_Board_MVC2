package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		int board_no=Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		int res =dao.boardDelete(board_no);
		
		
		PrintWriter out =response.getWriter();
		if(res==1) {
			out.println("<script>");
			out.println("alert('삭제성공!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
