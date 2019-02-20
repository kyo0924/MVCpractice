package com.rclass.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.board.model.service.BoardService;
import com.rclass.board.model.vo.Board;

/**
 * Servlet implementation class BoardCommentDelete
 */
@WebServlet("/board/boardCommentDelete")
public class BoardCommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		delNo은 삭제할 댓글 번호
		int delNo=Integer.parseInt(request.getParameter("delNo"));
//		게시글 번호		
		int boardNo=Integer.parseInt(request.getParameter("no"));

		int result=new BoardService().deleteComment(delNo);
	
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="댓글이 삭제되었습니다.";
			loc="/board/boardView?no="+boardNo;
		}
		else {
			msg="댓글 삭제가 실패하였습니다.";
			loc="/board/boardView?no="+boardNo;
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
