<%@page import="com.rclass.board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.rclass.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<%
	Board b = (Board) request.getAttribute("board");
	List<BoardComment> comments=(List)request.getAttribute("comments");
%>

<style>
section#board-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#board-container h2 {
	margin: 10px 0;
}

table#tbl-board {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-board td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}

table#tbl-comment {
	width: 580px;
	margin: 0 auto;
	border-collapse: collapse;
	clear: both;
}

table#tbl-comment tr td {
	border-bottom: 1px solid;
	border-top: 1px solid;
	padding: 5px;
	text-align: left;
	line-height: 120%;
}

table#tbl-comment tr td:first-of-type {
	padding: 5px 5px 5px 50px;
}

table#tbl-comment tr td:last-of-type {
	text-align: right;
	width: 100px;
}

table#tbl-comment button.btn-reply {
	display: none;
}
table#tbl-comment tr:hover button.btn-delete {
	display: inline;
}

table#tbl-comment tr:hover {
	background: lightgray;
}

table#tbl-comment tr:hover button.btn-reply {
	display: inline;
}
table#tbl-comment tr:hover button.btn-delete {
	display: inline;
}

table#tbl-comment tr.level2 {
	color: gray;
	font-size: 14px;
}

table#tbl-comment sub.comment-writer {
	color: navy;
	font-size: 14px
}

table#tbl-comment sub.comment-date {
	color: tomato;
	font-size: 10px
}

table#tbl-comment tr.level2 td:first-of-type {
	padding-left: 100px;
}

table#tbl-comment tr.level2 sub.comment-writer {
	color: #8e8eff;
	font-size: 14px
}

table#tbl-comment tr.level2 sub.comment-date {
	color: #ff9c8a;
	font-size: 10px
}

table#tbl-comment textarea {
	margin: 4px 0 0 0;
}

table#tbl-comment button.btn-insert2 {
	width: 60px;
	height: 23px;
	color: white;
	background: #3300ff;
	position: relative;
	top: -5px;
	left: 10px;
}
</style>

<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-board">
		<tr>
			<th>글번호</th>
			<td><%=b.getBoardNo()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=b.getBoardTitle()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=b.getBoardWriter()%></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=b.getReadCount()%></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><a
				href="javascript:fn_fileDown('<%=b.getBoardOriginalFileName()%>','<%=b.getBoardRenamedFileName()%>')">
					<%
						if (b.getBoardRenamedFileName() != null) {
					%> <img alt="첨부파일"
					src="<%=request.getContextPath()%>/images/file.png" width='13px' />
					<%=b.getBoardOriginalFileName()%> 
					<%}%>
			</a></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=b.getBoardContent()%></td>
		</tr>
		<!-- 관리자, 작성자 -->
		<%if (logginMember != null && (b.getBoardWriter().equals(logginMember.getUserId())
					|| "admin".equals(logginMember.getUserId()))) {%>
		<tr>
			<td colspan="2">수정삭제 버튼 *관리자, 본인일 경우 
			<input type="button" value="수정하기" onclick="fn_updateBoard()" /> 
				<input type="button" value="삭제하기" onclick="fn_deleteBoard()" />
			</td>
		</tr>
		<%}%>
	</table>

	<div id="comment-container">
		<div class="comment-editer">
			<form name="commentFrm"
				action="<%=request.getContextPath()%>/board/boardCommentInsert"
				method="post">
				<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>" />
				<input type="hidden" name="boardCommentWriter" value="<%=logginMember!=null?logginMember.getUserId():"" %>" /> 
				<input type="hidden" name="boardCommentLevel" value="1" /> 
				<input type="hidden" name="boardCommentRef" value="0" />
				<textarea name="boardCommentContent" cols="60" row="3"></textarea>
				<button type="submit" id="btn-insert">등록</button>
			</form>
		</div>
		<table id="tbl-comment">
			<%if(comments!=null){ 
			for(BoardComment bc : comments) {
			if(bc.getBoardCommentLevel()==1){%>
			<tr class="level1">
				<td><sub class="comment-writer"><%=bc.getBoardCommentWriter() %></sub>
					<sub class="comment-date"><%=bc.getBoardCommentDate() %></sub> <br/> <%=bc.getBoardCommentContent() %>
				</td>
				<td>
					<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">답글</button>
					<%if(logginMember!=null&&(bc.getBoardCommentWriter().equals(logginMember.getUserId())||"admin".equals(logginMember.getUserId()))) { %>
					<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
					<%} %>
				</td>
			</tr>
			<%} 
			else{%>
			<tr class='level2'>
				<td><sub><%=bc.getBoardCommentWriter() %></sub> <sub><%=bc.getBoardCommentDate() %></sub>
					<br /> <%=bc.getBoardCommentContent() %></td>
				<td></td>
			</tr>
			<%}%>
			<%} 
			}%>
		</table>
	</div>
</section>

<script>
$(function(){
	$(".btn-delete").on("click", function(){
		if(!confirm("정말로 삭제하시겠습니까?")) return;		/* b.getBoardNo()는 되돌아오려고 보내는거고  delNo은 실제로 지울 넘버*/
		location.href="<%=request.getContextPath()%>/board/boardCommentDelete?no=<%=b.getBoardNo()%>&delNo="+$(this).val();
																		/* 여기서 this는 삭제버튼 */
	});
	
})




/* 대댓글 만드는 과정 
 * 댓글에 들어가 있는 컬럼이 필요하다 이 게시판이 뭔지? 글쓴이? 내용? 날짜는 defualt
 * 댓글에 대한 답글이니깐 부모가 2개 - 게시판, 댓글 => 그래서 히든으로 필요한값을 다 가져와야함
 * 글쓴이(Writer)는 로그인되있는 id의 getUserId로 받아와야함
 */
	$(".btn-reply").on("click", function(){
		/* 아래 구문 = 로그인이 되있어야 댓글을 남길수 있다. */
		
		<%if(logginMember!=null) {%>
			var tr=$("<tr></tr>");
			var html="<td style='display:none;text-align:left;' colspan=2>";
			html+="<form action='<%=request.getContextPath()%>/board/boardCommentInsert' method='post'>";
			html+="<input type='hidden' name='boardRef' value='<%=b.getBoardNo()%>'/>";
			html+="<input type='hidden' name='boardCommentWriter' value='<%=logginMember.getUserId()%>'/>";
			html+="<input type='hidden' name='boardCommentLevel' value='2'/>";
			html+="<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'/>";
			/* 여기서 this는 답글 버튼 */
			html+="<textarea cols='60' rows='1' name='boardCommentContent'></textarea>";
			html+="<button type='submit' class='btn-insert2'>등록</button>";
			html+="</form></td>";
			tr.html(html);
			/* parent()는 상위개념으로 하나씩 올라감 */
			tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
			
			$(this).off('click');
			
			tr.find("form").submit(function(){
				if(<%=logginMember==null%>){
					fn_loginAlert();
					event.preventDefault();
					return;
				}
				var content=$(this).children('textarea').val().trim().length;
				if(content==0){
					alert("내용을 입력하세요");
					event.preventDefault();
					return;
				}
			});
			<%}
		else{%>
		fn_loginAlert();
		<%}%>
	});

	$(function(){
		$('[name=boardCommentContent]').focus(function(){
			
			if(<%=logginMember== null%>){
				fn_loginAlert();
				$("#UserId").focus();
			}
		});
		$("[name=commentFrm]").submit(function(e){
			if(<%=logginMember==null%>){
				fn_loginAlert();
				e.preventDefault();
				return;
			}
			var len=$("textarea[name=boardCommentContent]").val().trim().length;
			if(len==0){
				alert("내용을 입력하세요");
				e.preventDefault();
			}
		});
	});
	function fn_loginAlert(){
		alert("로그인 후 이용할 수 있습니다.");
		$("[name=UserId]").focus();
	}

<%-- 파일 다운로드 내 풀이	
	function fn_fileDown(fname){
		fname=encodeURIComponent(fname);
		location.href="<%=request.getContextPath() %>/board/boardFileDownload?fname="+fname;
	}	--%>
					
					/* 강사님 풀이 */
	function fn_fileDown(oName, rName){
		var url="<%=request.getContextPath()%>/board/boardFileDownload";
		oName=encodeURIComponent(oName);
		location.href=url+'?oName='+oName+"&rName="+rName;
	}
	</script>

<%@include file="/views/common/footer.jsp"%>