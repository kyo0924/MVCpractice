package com.rclass.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.board.model.service.BoardService;
import com.rclass.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardRef=Integer.parseInt(request.getParameter("boardRef"));
		String boardCommentWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = request.getParameter("boardCommentContent");
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
	
		BoardComment comment = new BoardComment();
		comment.setBoardRef(boardRef);
		comment.setBoardCommentContent(boardCommentContent);
		comment.setBoardCommentLevel(boardCommentLevel);
		comment.setBoardCommentRef(boardCommentRef);
		comment.setBoardCommentWriter(boardCommentWriter);
	
		int result = new BoardService().insertBoardComment(comment);
	
		String msg="";
		String view="/views/common/msg.jsp";
		String loc="/board/boardView?no="+boardRef;
		if(result>0) {
			msg="등 록 성 공";
		}
		else {
			msg="등 록 실 패";
		}
		
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
