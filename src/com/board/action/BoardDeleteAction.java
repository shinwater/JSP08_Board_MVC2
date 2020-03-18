package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제를 누르면 글이 삭제됨!
		int board_no = Integer.parseInt(request.getParameter("no"));
		
		return null;
	}

}
