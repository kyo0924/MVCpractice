<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rclass.notice.model.vo.Notice" %>
<%
	Notice n=(Notice)request.getAttribute("notice");

%>    
<%@ include file="/views/common/header.jsp" %>
     <style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    </style>
	<section id="notice-container">
		<table id="tbl-notice">
			<tr>
				<th>제목</th>
				<td><%=n.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=n.getNoticeWriter() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<%if(n.getFilePath()!=null){ %>
					<a href="javascript:fn_fileDown('<%=n.getFilePath()%>')">
						<img alt="첨부파일" 
						src="<%=request.getContextPath() %>/images/file.png"
						width="16px"/>
					</a>
					<script>
						function fn_fileDown(fname)
						{
							fname=encodeURIComponent(fname);
							location.href="<%=request.getContextPath() %>/notice/noticeFileDownload?fname="+fname;
						}
					</script>
					<%} %>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><%=n.getNoticeContent() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="수정하기" onclick="fn_updateNotice()"/>
					<input type="button" value="삭제하기" onclick="fn_deleteNotice()"/>
				</td>
			</tr>
		</table>
		<form name="deleteFrm" action="<%=request.getContextPath() %>/noticeDelete" method="post">
			<input type="hidden" name="deleteNo" value="<%=n.getNoticeNo() %>"/>
			<input type="hidden" name="deleteFile" value="<%=n.getFilePath() %>"/>
		</form>
		<script>
			function fn_updateNotice()
			{
				location.href="<%=request.getContextPath()%>/notice/noticeUpdate?no=<%=n.getNoticeNo()%>";
			}
			function fn_deleteNotice()
			{
				var flag=confirm("게시물을 지우시겠습니까?");
				if(flag)
				{
					deleteFrm.submit();
				}
				else{
					return;
				}
			}
		</script>	
	</section>
<%@include file="/views/common/footer.jsp" %>







