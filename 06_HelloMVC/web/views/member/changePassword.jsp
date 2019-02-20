<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId=request.getParameter("userId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드변경</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
	div#chagePassword-container{
		background-color:red;
	}
	div#chagePassword-container table{
		margin:0 auto;
		border-spacing:20px;
	}
	div#chagePassword-container table tr:last-of-type td{
		text-align:center;
	}
</style>
</head>
<body>
	<div id='chagePassword-container'>
		<form name="chagePassword" 
		action="<%=request.getContextPath() %>/changePasswordEnd" 
		method="post">
		<table>
			<tr>
				<th>현재 비밀번호</th>
				<td>
					<input type="password" name="password" id="password" required/>
				</td>	
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td>
					<input type="password" name="password_new" id="password_new"
					 required/>
				</td>
			</tr>
			<tr>
				<th>비밀번호 확인</th>
				<td>
					<input type="password" id="password_ck" required/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" 
					onclick="return password_validate();" value="변경"/>
					&nbsp;
					<input type="button" onclick="self.close()" value="닫기"/>
				</td>
			</tr>
		</table>
		<input type="hidden" name="userId" value="<%=userId %>"/>
	</form>
	</div>
	<script>
		function password_validate()
		{
			var pwNew=$('#password_new').val();
			var pwCk=$('#password_ck').val();
			if(pwNew!=pwCk)
			{
				alert('입력한 패스워드가 일치하지 않습니다.');
				$('#password_new').select();
				return false;
			}
			return true;
		}
	
	
	</script>
</body>
</html>














