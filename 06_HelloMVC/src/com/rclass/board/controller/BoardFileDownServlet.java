package com.rclass.board.controller;

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
 * Servlet implementation class BoardFileDownServlet
 */
@WebServlet("/board/boardFileDownload")
public class BoardFileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String rName=request.getParameter("rName");
		String oName=request.getParameter("oName");
/*	
*		1.실제 파일의 저장경로를 가져오기
*		2.파일 입출력 스트림 생성(input, out(servlet))
*		3.브라우저에 따른 파일명 처리(인코딩) - 한글	
*		4.response.header에 content-type:application/otect-stream, 
*		  content-Disposition:attachment; filename=파일명
*		5.파일을 outputStream에 write하면 됨.
*/
		
//		1실제 파일 저장경로 가져오기
		String dir = getServletContext().getRealPath("/upload/board/");
		
//		2. 파일생성!
		File downFile = new File(dir+File.separator+rName);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downFile));
//		서버에 있는 파일을 ram으로 전송! 올림!
		
//		보낼 스트림 작성(outputStream)
		ServletOutputStream sos=response.getOutputStream();
		BufferedOutputStream bos=new BufferedOutputStream(sos);
//		보낼 클라이언트에 스트림 연결!
		
//		3. 한글파일을 위한 파일명 분기처리(깨지는거 방지)
		String resFileName="";
		boolean isMSIE=request.getHeader("user-agent").indexOf("MSIE")!=-1
				||request.getHeader("user-agent").indexOf("Trident")!=-1;
		
		if(isMSIE) {
			resFileName=URLEncoder.encode(oName, "UTF-8").replaceAll("\\", "%20");
		}
		
		else {
			resFileName=new String(oName.getBytes("UTF-8"),"ISO-8859-1");
		}
		
//		4.헤더 세팅
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+resFileName);
		
//		5.파일 보내주기
		int read=-1;
		while((read=bis.read())!=-1) {
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
