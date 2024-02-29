

<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.mycompany.goldenTime.dao.MRepository" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% String modifyMessage = (String)request.getAttribute("modifyMessage"); %>

<% if(modifyMessage != null && !modifyMessage.isEmpty()) { %>
	<script language="javascript">
		alert("<%= modifyMessage%>");
		<% if (modifyMessage.equals("회원정보가 성공적으로 업데이트 되었습니다.")) { %>
         	window.location.href = "/goldenTime/";
         <% } %>
		
	</script>
<% } %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
