<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member m=(Member)request.getAttribute("member");
	String[] hobbys=m.getHobby().split(",");
	//dd,ff,gg,rr
	String[] checkHobby=new String[6];
	for(int i=0;i<hobbys.length;i++)
	{
		switch(hobbys[i])
		{
			case "독서" : checkHobby[0]="checked";break;
			case "꽃꽂이" : checkHobby[1]="checked";break;
			case "십자수" : checkHobby[2]="checked";break;
			case "게임" : checkHobby[3]="checked";break;
			case "승마" : checkHobby[4]="checked";break;
			case "펜싱" : checkHobby[5]="checked";break;			
		}
	}
	

%>    
    
<style>

	#userId_{
		background-color:lightgray;
	}

</style>    
<%@ include file="/views/common/header.jsp" %>
<section id="enroll-container">
	<h2>회원정보 수정</h2>
	<form name='memberFrm' action="<%=request.getContextPath() %>/memberUpdateEnd" 
	onsubmit="return fn_enroll_validate();" method="post">
	<table>
		<tr>
			<th>
				아이디
			</th>
			<td>
				<input type="text" name='userId' id="userId_" 
				readonly value="<%=m.getUserId()%>"/>
			</td>
		</tr>
<!-- 		<tr>
			<th>
				패스워드
			</th>
			<td>
				<input type="password" name="password" id="password_"
				required />
			</td>
		</tr>
		<tr>
			<th>패스워드확인</th>
			<td>
				<input type="password" id="password_2" required/>
			</td>
		</tr> -->
		<tr>
			<th>이름</th>
			<td>
				<input type="text" name="userName" id="userName" 
				required value="<%=m.getUserName()%>"/>
			</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>
				<input type="number" name="age" id="age"
				value="<%=m.getAge()%>">
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="email" placeholder="abc@dse.com" name="email" 
				id="email" value="<%=m.getEmail()%>"/>
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<input type="tel" placeholder="(-없이)01012345678" 
				name='phone' id="phone" maxlength="11" 
				required value="<%=m.getPhone()%>"/>
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text"  
				name='address' id="address" value="<%=m.getAddress()%>" 
				required/>
			</td>
		</tr>		
		<tr>
			<th>성별</th>
			<td>
				<input type="radio" name="gender" id="gender0"
				value="M" <%=m.getGender().equals("M")?"checked":"" %> /><label for="gender0">남</label>
				<input type="radio" name="gender" id="gender1"
				value="F" <%=m.getGender().equals("F")?"checked":"" %>/><label for="gender1">여</label>
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<input type="checkbox" name="hobby" id="hobby0" 
				value="독서" <%=checkHobby[0] %>><label for="hobby0">독서</label>
				<input type="checkbox" name="hobby" id="hobby1" 
				value="꽃꽂이" <%=checkHobby[1] %>><label for="hobby1">꽃꽂이</label>
				<input type="checkbox" name="hobby" id="hobby2" 
				value="십자수" <%=checkHobby[2] %>><label for="hobby2">십자수</label><br/>
				<input type="checkbox" name="hobby" id="hobby3" 
				value="게임" <%=checkHobby[3] %>><label for="hobby3">게임</label>
				<input type="checkbox" name="hobby" id="hobby4" 
				value="승마" <%=checkHobby[4] %>><label for="hobby4">승마</label>
				<input type="checkbox" name="hobby" id="hobby5" 
				value="펜싱" <%=checkHobby[5] %>><label for="hobby5">펜싱</label>
			</td>
		</tr>
	</table>
	<input type="button" onclick="fn_updateMember();" value="정보수정"/>
	<input type="button" onclick="fn_changePw();" value="비밀번호변경"/>
	<input type="button" onclick="fn_deleteMember();" value="탈퇴"/>
	</form>
	<script>
		function fn_changePw()
		{
			var url="<%=request.getContextPath()%>/changePassword?userId=<%=m.getUserId()%>";
			var title="changePassword";
			var option="left=100px, top=100px,width=400px, height=210px";
			var popup=window.open(url,title,option);
		}
		function fn_deleteMember()
		{
			var flag=confirm("정말로 탈퇴하시겠습니까?");
			if(flag)
			{
				location.href="<%=request.getContextPath()%>/memberDelete?userId=<%=m.getUserId()%>";	
			}
			
		}
		function fn_updateMember()
		{
			memberFrm.submit();
		}
		$(function(){
			//패스워드일치여부 확인
			$('#password_2').blur(function(){
				var pw=$('#password_').val().trim();
				var pw2=$('#password_2').val().trim();
				if(pw!=pw2)
				{
					alert("패스워드가 일치하지 않습니다.");
					$('#password_').val('');
					$('#password_2').val('');
					$('#password_').focus();
				}
			});	
		})
		
	
	</script>
</section>





<%@include file="/views/common/footer.jsp" %>