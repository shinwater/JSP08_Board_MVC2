package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String find_field = request.getParameter("find_field");
		String find_name = request.getParameter("find_name");
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.boardSearch(find_field,find_name);
		
		request.setAttribute("List", list);
		
		return "board_search.jsp";
	}

}
