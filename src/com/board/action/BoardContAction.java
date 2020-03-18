package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글제목을 클릭시 상세내용을 보여주는 클래스 
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = 
				dao.boardCont(board_no); //상세내역 조회 메서드
		
		request.setAttribute("cont", dto);
		
		return "board_cont.jsp";
	}

}
