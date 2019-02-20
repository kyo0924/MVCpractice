package com.rclass.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeFileDownServlet
 */
@WebServlet("/notice/noticeFileDownload")
public class NoticeFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname=request.getParameter("fname");
		/*
		  1.실제파일의 저장경로를 가져오기
		  2.파일입출력 스트림생성(input, out(servlet))
		  3.브라우저에 따른 파일명 처리(인코딩) *한글
		  4.response.header에 content-type:application/otect-stream,
		    content-Disposition:attachment;filename=파일명
		  5.파일을 outputstream write하면 됨
		 */
		 //1.실제파일 저장경로 가져오기
		 String dir=getServletContext().getRealPath("/upload/notice");
		 
		 //2. 입출력스트림 생성하기
		 File downFile=new File(dir+File.separator+fname);
		 
		 BufferedInputStream bis
		 =new BufferedInputStream(new FileInputStream(downFile));
		 //서버에 있는 파일을 RAM으로 전송! 올림!
		 
		 //보낼 스트림작성(outputStream)
		 ServletOutputStream sos=response.getOutputStream();
		 BufferedOutputStream bos=new BufferedOutputStream(sos);
		 //보낼 클라이언트에 스트림 연결!
		 
		 
		 //3. 한글파일을 위한 파일명 분기처리(깨지는거 방지)
		 String resFileName="";
		 boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1
				 ||request.getHeader("user-agent").indexOf("Trident")!=-1;
		 if(isMSIE)
		 {
			 resFileName=URLEncoder.encode(fname,"UTF-8").replaceAll("\\", "%20");
		 }
		 else
		 {
			 resFileName=new String(fname.getBytes("UTF-8"),"ISO-8859-1");
		 }
		 
		 //4.해더세팅
		 response.setContentType("application/octet-stream");
		 response.setHeader("Content-Disposition", "attachment;filename="+resFileName);
		 /*response.setHeader("Content-Disposition", "inline;filename="+resFileName);*/
		 //5.파일 보내주기!
		 int read=-1;
		 while((read=bis.read())!=-1)
		 {
			 bos.write(read);
		 }
		 
		 bis.close();
		 bos.close();		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
