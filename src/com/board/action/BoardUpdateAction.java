package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		//dto로 값ㅂ받아서 키로 보내줘야지이
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO list =dao.boardUpdate(board_no);
		request.setAttribute("List", list);
		
		return "board_update.jsp";
		
	}

}
