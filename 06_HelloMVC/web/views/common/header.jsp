<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rclass.member.model.vo.Member" %>    
<%
	Member logginMember=(Member)session.getAttribute("logginMember");
	//saveId를 가져와보자~! 고고
	//Cookie는 request의 getCookies()로 가져올 수 있음 
	//반환형이 배열
	Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c:cookies)
		{
			String key=c.getName();
			String value=c.getValue();
			if(key.equals("saveId"))
			{
				saveId=value;
				break;
			}
		}
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel='stylesheet' href='<%=request.getContextPath()%>/css/style.css' type='text/css'/>
</head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	function validate(){
		var idVal=$('[name=userId]').val();
		var pwVal=$('[name=password]').val();
		
		if(idVal.trim().length==0)
		{
			alert("아이디를 입력하세요");
			$('[name=userId]').focus();
			return false;
		}
		if(pwVal.trim().length==0)
		{
			alert("비밀번호를 입력하세요!");
			$('[name=password]').focus();
			return false;
		}
		return true;
	}


</script>
<body>
<header>
	<h1>Hello MVC</h1>
	<div class='login-container'>
	<% if(logginMember==null) {%>
		<form id="loginFrm" action="<%=request.getContextPath() %>/login"
		method="post" onsubmit="return validate();">
		<table>
			<tr>
				<td>
				<input type="text" name="userId" placeholder="아이디" value="<%=saveId!=null?saveId:"" %>"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<input type="password" name="password" placeholder="비밀번호"/>
				</td>
				<td>
					<input type="submit" value="로그인"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type='checkbox' name="saveId"
					id="saveId" <%=saveId!=null?"checked":""%>/>
					<label for="saveId">아이디저장</label>
					<input type='button' value="회원가입"
					onclick="location.href='<%=request.getContextPath()%>/memberEnroll'"/>
				</td>
			</tr>
		</table>
		</form>
		<%}else{ %>
		<table id='logged-in'>
			<tr>
				<td><%=logginMember.getUserId() %>님, 안녕하세요</td>
			</tr>
			<tr>
				<td>
					<input type="button" value="내정보보기" onclick="location.href='<%=request.getContextPath()%>/updateMember?userId=<%=logginMember.getUserId()%>'"/>
					<input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/logout'"/>
				</td>
			</tr>
		</table>
		<%} %>
	</div>
	<nav>
		<ul class="main-nav">
			<li class="home"><a href="<%=request.getContextPath() %>">HOME</a></li>
			<li id="notice"><a href="<%=request.getContextPath() %>/notice/noticeList">공지사항</a></li>
			<li id="board"><a href="<%=request.getContextPath() %>/board/boardList">게시판</a></li>
			<li id="photo-board"><a href="<%=request.getContextPath() %>">갤러리</a></li>
			<%if(logginMember!=null&&"admin".equals(logginMember.getUserId())) {%>
				<li id="admin-member"><a href="<%=request.getContextPath() %>/admin/memberList">회원관리<span class="animate_line"></span></a></li>
			<%} %>
		</ul>
	</nav>
</header>













