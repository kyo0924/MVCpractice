package com.rclass.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.rclass.board.model.service.BoardService;
import com.rclass.board.model.vo.Board;

import common.rename.MyFileRenamePolicy;
//등록하기
/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request))
		{
			request.setAttribute("msg", "잘못접근! 조심!");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);;
		}
		
		String root=getServletContext().getRealPath("/upload");
		String filePath=root+"/board";
		
		int maxSize=1024*1024*10;
		//파일 업로드 관련
		MultipartRequest mr=new MultipartRequest(request, filePath,maxSize,"UTF-8",	new MyFileRenamePolicy());
		
		Board b=new Board();
		b.setBoardTitle(mr.getParameter("title"));
		b.setBoardWriter(mr.getParameter("writer"));
		b.setBoardContent(mr.getParameter("content"));
		b.setBoardOriginalFileName(mr.getOriginalFileName("up_file"));
		b.setBoardRenamedFileName(mr.getFilesystemName("up_file"));
		
		int result=new BoardService().insertBoard(b);
		
		String msg="";
		String loc="/board/boardList";
		String view="/views/common/msg.jsp";
		if(result>0)
		{
			msg="게시글 등록성공";
		}
		else {
			msg="게시글 등록 실패";
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
