<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <!-- euc=kr > utf-8로 변경 -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그아웃 액션</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	<script>
		location.href = 'login.jsp';
	</script>
</body>
</html>