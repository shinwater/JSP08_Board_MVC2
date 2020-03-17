package com.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// 실제로 DB에서 게시글 전체 리스트를 조회하는 컨트롤러~.~
		BoardDAO dao =BoardDAO.getInstance();
		
		 List<BoardDTO> list =dao.getBoardList();
		
		 request.setAttribute("List", list);
		return "board_list.jsp"; //이동할 페이지 주소...
	}

}
