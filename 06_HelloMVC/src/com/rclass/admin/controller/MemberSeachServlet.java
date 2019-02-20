package com.rclass.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.admin.model.service.AdminService;
import com.rclass.member.model.vo.Member;

/**
 * Servlet implementation class MemberSeachServlet
 */
@WebServlet("/admin/memberFinder")
public class MemberSeachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private AdminService service=new AdminService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSeachServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("searchType");
		String key=request.getParameter("searchKeyword");
		int cPage;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e)
		{
			cPage=1;
		}
		int numPerPage;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}
		catch(NumberFormatException e)
		{
			numPerPage=5;
		}		
		int totalcontent=0;
		List<Member> list=null;
		
		totalcontent=service.selectMemberCount(type,key);
		list=service.selectSearchMember(type,key,cPage, numPerPage);
		
		int totalPage=(int)Math.ceil((double)totalcontent/numPerPage);
		
		
		//pageBar구성
		int pageBarSize=5;//bar에 출력할 페이지수
		String pageBar="";//bar를 만든 소스코드(html)
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		//시작지점 1, 6, 11, 16
		//기준 : cPage 1~5 : 1 / cPage 6~10 : 6
		int pageEnd=pageNo+pageBarSize-1;
		
		//페이지에 출력해줄 소스코드 작성
		//[이전] 코드작성
		if(pageNo==1) {
			pageBar+="<span>[이전]</span>";
		}
		else {
			pageBar+="<a href='"+request.getContextPath()
			+"/admin/memberFinder?cPage="+(pageNo-1)
			+"&numPerPage="+numPerPage+"&searchType="+type+"&searchKeyword="+key+"'>[이전]</a>";
		}
		//페이지연결 숫자 소스작성
		while(!(pageNo>pageEnd||pageNo>totalPage))
		{
			if(cPage==pageNo)
			{
				pageBar+="<span class='cPage'>"+pageNo+"</span>";
			}
			else 
			{
				pageBar+="<a href='"+request.getContextPath()
				+"/admin/memberFinder?cPage="+pageNo
				+"&numPerPage="+numPerPage+"&searchType="+type+"&searchKeyword="+key+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//[다음] 코드작성
		if(pageNo>totalPage)
		{
			pageBar+="<span>[다음]</span>";
		}
		else {
			pageBar+="<a href='"+request.getContextPath()
			+"/admin/memberFinder?cPage="+pageNo
			+"&numPerPage="+numPerPage+"&searchType="+type+"&searchKeyword="+key+"'>[다음]</a>";
		}

		
		
		request.setAttribute("searchType", type);
		request.setAttribute("searchKeyword", key);	
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage",numPerPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/memberList.jsp")
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
