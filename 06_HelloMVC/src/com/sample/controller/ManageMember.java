package com.sample.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.member.model.vo.Member;
import com.sample.model.service.SampleService;

/**
 * Servlet implementation class ManageMember
 */
@WebServlet("/sample")
public class ManageMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cPage;
		try
		{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}
		catch(NumberFormatException e)
		{
			cPage=1;
		}
		int numPerPage=10;
		
		//페이징처리를 위한 전체 자료수~
		int totalCount=new SampleService().selectCount();
		
		List<Member> list=new SampleService().selectList(cPage,numPerPage);
		
		//페이지바
		//전체페이지 수 
		int totalPage=(int)Math.ceil((double)totalCount/numPerPage);
		
		int pageBarSize=5;
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="";
		
		if(pageNo==1)
		{
			pageBar+="<span>이전</span>";
		}
		else {
			pageBar+="<a href='"+request.getContextPath()
			+"/sample?cPage="+(pageNo-1)
					+"&numPerPage="+numPerPage+"'>이전</a>";
		}
		
		//페이지연결숫자 소스작성
		while(!(pageNo>pageEnd||pageNo>totalPage))
		{
			if(cPage==pageNo)
			{
				pageBar+="<span>"+pageNo+"</span>";
			}
			else
			{
				pageBar+="<a href='"+request.getContextPath()
				+"/sample?cPage="+pageNo
						+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		//다음 코드를 작성
		
		if(pageNo>totalPage)
		{
			pageBar+="<span>다음</span>";
		}
		else 
		{
			pageBar+="<a href='"+request.getContextPath()
			+"/sample?cPage="+pageNo+"'>다음</a>";
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("cPage",cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar",pageBar);
		request.getRequestDispatcher("views/sample/list.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
