<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <!-- euc=kr > utf-8로 변경 -->
        <%@ page import="wifi.WifiService" %>
        <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>wifi DB 정보 업데이트</title>
</head>
<body>
	<%
		WifiService wifi = new WifiService();
		int result = wifi.updateWifi();
		
		String resultStr = "";
		if(result == 0){
			resultStr = "이미 업데이트가 되었습니다.";
		} else {
			resultStr = "와이파이 정보가 업데이트 되었씁니다.";
		}
	%>
	<h1><%=resultStr%></h1>
</body>
</html>