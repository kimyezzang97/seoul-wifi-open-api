<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <!-- euc=kr > utf-8로 변경 -->
        <%@ page import="wifi.WifiService" %>
        <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bookMark 삭제</title>
<script>
alert('제거되었습니다');
</script>
</head>

<body>
	<%
		String user_id = request.getParameter("id");
  		String wifi_no = request.getParameter("wifi_no");
		WifiService wifi = new WifiService();
		 wifi.bookMarkDelete(user_id, wifi_no);
		
	%>
</body>
</html>