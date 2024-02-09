<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <!-- euc=kr > utf-8로 변경 -->
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="java.util.*" %>
    <%@ page import="wifi.Wifi" %>
<%@ page import="wifi.WifiService" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel ="stylesheet" href="css/bootstrap.css">
<title>북마크 추가</title>
<script>

</script>
</head>
<body>
	<% 
      	String user_id = request.getParameter("id");
      	String wifi_no = request.getParameter("wifi_no");
      	
      	WifiService wifi = new WifiService();
      	wifi.bookMarkAdd(user_id, wifi_no);
	%>
	<h1><%= user_id%>의 <%=wifi_no %>가 추가되었습니다</h1>
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
</body>
</html>