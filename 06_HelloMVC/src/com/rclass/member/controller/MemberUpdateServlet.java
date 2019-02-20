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
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/updateMember")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인한 회원의 정보를 가져와서 그 정보를 출력해주는 view화면을 선택
		String userId=request.getParameter("userId");
		Member m=new Member();
		m.setUserId(userId);
		//비지니스로직
		Member result=new MemberService().selectOne(m);
		
		//view선택
		String msg="";
		String loc="";
		String view="";
		if(result==null)
		{
			msg="해당회원이 존재하지 않습니다.";
			view="/views/common/msg.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("loc",loc);
		}
		else 
		{
			view="/views/member/memberView.jsp";
			request.setAttribute("member",result);
		}
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
