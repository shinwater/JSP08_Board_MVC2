package com.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정을 누르면 해당번호의 데이터을 가지고!!!!!!! 수정폼창으로 넘어가자!
		
		int board_no = Integer.parseInt(request.getParameter("no"));
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO list =dao.boardUpdate(board_no);
		
		request.setAttribute("List", list);
		
		return "board_update.jsp";
	}

}
