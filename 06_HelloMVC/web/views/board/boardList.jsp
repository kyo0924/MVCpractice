<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.rclass.board.model.vo.*"%>
<%
	List<Board> list=(List)request.getAttribute("list");
%>


<%@ include file="/views/common/header.jsp" %>
  <style>
  section#board-container{width:600px; margin:0 auto; text-align:center;}
  section#board-container h2{margin:10px 0;}
  table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
  table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;}
  input#btn-add{float:right;margin:0 0 15px;} 
  </style>
<section id="board-container">
	<h2>게시판</h2>
	<%if(logginMember!=null) {%>
	<input type="button" value="글쓰기" id="btn-add" 
	onclick="fn_boardForm()"/>
	<%} %>
	<script>
		function fn_boardForm(){
			location.href="<%=request.getContextPath()%>/board/boardForm";
		}
	
	</script>
	
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>첨부파일</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<%for(Board n:list) {%>
		<tr>
			<td><%=n.getBoardNo() %></td>
			<td><a href="<%=request.getContextPath()%>/board/boardView?no=<%=n.getBoardNo()%>"><%=n.getBoardTitle() %></a></td>
			<td><%=n.getBoardWriter() %></td>
			<td>
			<%if(n.getBoardOriginalFileName()!=null) {%>
				<img alt="첨부파일" src="<%=request.getContextPath()%>/images/fi le.png" width="16px">
			<%}%>
			</td>
			<td><%=n.getBoardDate() %></td>
			<td><%=n.getReadCount() %></td>
		</tr>
		<%} %>
	</table>
	<div id="pageBar">
		<%=(String)request.getAttribute("pageBar") %>
	</div>
</section>


<%@include file="/views/common/footer.jsp" %>



