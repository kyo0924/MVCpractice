package com.rclass.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.board.model.service.BoardService;
import com.rclass.board.model.vo.Board;
import com.rclass.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardViewEndServlet
 */
@WebServlet("/board/boardView")
public class BoardViewEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardViewEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo=Integer.parseInt(request.getParameter("no"));

		//		COOKIE
		Cookie[] cookies=request.getCookies();
		String boardCookieVal="";
		//		처음에는 안읽었다고 선언해놓음
		boolean hasRead=false;

		//		원하는 쿠키가 없다면? - 읽지 않았다! 라는 소리
		if(cookies!=null) {
			//null이면 읽지 않았으니깐 진행하면 됨
			output : for(Cookie c : cookies) {
				String name=c.getName();
				String value=c.getValue();
				if("boardCookie".equals(name)) {
					boardCookieVal=value;
					if(value.contains("|"+boardNo+"|")) {	
						//value가 boardNo을 갖고 있으면 읽었다.
						hasRead=true;
						break output;
					}
				}
			}
		}
		
		
		//boardCookie를 세팅
		if(!hasRead) {
			//			boardCookie 라는 변수에 집어넣어주는 것
			Cookie c = new Cookie("boardCookie", boardCookieVal+"|"+boardNo+"|");
			// 브라우저가 닫히거나 세션이 종료됬을 때 쿠키값을 지워요 - 쿠키는 클라이언트가 가지고 있음
			c.setMaxAge(-1);
			// -1 대신에 60*60*24로 해서 하루기간동안 가지고 있을 수 있도록 할 수 있음

			//			쿠키가 저장이 됨
			response.addCookie(c);
		}

		Board b=new BoardService().selectOne(boardNo, hasRead);
		
		if(b!=null) {
			List<BoardComment> comments = new BoardService().selectCommentAll(boardNo);
			request.setAttribute("comments", comments);
		}

		request.setAttribute("board", b);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
