package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardCheckAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no"));
		int cn = Integer.parseInt(request.getParameter("cn"));
		
		
		request.setAttribute("no", board_no);
		request.setAttribute("cn", cn);
		
		return "board_check.jsp";
 				
	}

}
