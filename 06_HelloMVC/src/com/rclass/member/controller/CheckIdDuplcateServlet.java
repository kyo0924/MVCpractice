package com.rclass.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.member.model.service.MemberService;
import com.rclass.member.model.vo.Member;

/**
 * Servlet implementation class CheckIdDuplcateServlet
 */
@WebServlet("/checkIdDuplicate")
public class CheckIdDuplcateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdDuplcateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member m=new Member();
		String userId=request.getParameter("userId");
		m.setUserId(userId);
		Member result=new MemberService().selectOne(m);
		System.out.println(result);	
		boolean isAble=(result==null?true:false);
		
		//응답화면선택
		request.setAttribute("userId", userId);
		request.setAttribute("isAble",isAble);
		
		request.getRequestDispatcher("/views/member/checkDuplicate.jsp")
		.forward(request, response);
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
