<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
<% 
	List<Member> list=(List)request.getAttribute("list");
	String pageBar=(String)request.getAttribute("pageBar");
	int cPage=(int)request.getAttribute("cPage");
	int numPerPage=(int)request.getAttribute("numPerPage");
%>
<%@ include file="/views/common/header.jsp" %>
	<table id="tbl-member">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>나이</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>취미</th>
			<th>가입일</th>
		</tr>
		<%if(list!=null){
			for(int i=0;i<list.size();i++){%>
			<tr>
				<td>
					<%=list.get(i).getUserId() %>
				</td>
				<td>
					<%=list.get(i).getUserName() %>
				</td>
				<td>
					<%=list.get(i).getGender() %>
				</td>
				<td>
					<%=list.get(i).getAge() %>
				</td>
				<td>
					<%=list.get(i).getEmail() %>
				</td>
				<td>
					<%=list.get(i).getPhone() %>
				</td>
				<td>
					<%=list.get(i).getAddress() %>
				</td>
				<td>
					<%=list.get(i).getHobby() %>
				</td>
				<td>
					<%=list.get(i).getEnrollDate() %>
				</td>				
			</tr>
		<% }
		}%>
	</table>
	<%=pageBar %>

<%@include file="/views/common/footer.jsp" %>







