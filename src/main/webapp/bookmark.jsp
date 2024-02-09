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
<title>Book MarkList</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script>
function bookMarkDelete(wifi_no){
	window.open('http://www.yezzang.pe.kr:1005/bookMarkDelete.jsp?id='+
			document.getElementById('member_id').value+
			'&wifi_no='+wifi_no,
			"_blank","width=400,height=400,left=200,top=200");
	
	location.reload(true);
	}

</script>
</head>
<body>
	<%
		String member_id = null;
		if(session.getAttribute("member_id") != null){
			member_id = (String) session.getAttribute("member_id");
		}
	%>
	<input type="hidden" id="member_id" value="<%= member_id%>"/>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collpase" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">메인 화면</a>
		</div>
		<div class="collapse navbar-collapse" id ="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav" aria-labelledby="dLabel">
				<li ><a href="main.jsp">메인</a></li>
				<li class="active"><a href="bookmark.jsp">북마크</a></li>
				<li><a href="logoutAction.jsp">로그아웃</a></li>
			</ul>
			<%
				if(member_id == null){
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('로그인을 해주세요.')");
					script.println("location.href = 'login.jsp'");
					script.println("</script>");
				}
			%>
		</div>
	</nav>
	
	<div class="container">
		<div class="row">
			<table class="table tblae-striped" style="text-align: center; border: 1px solid #dddddd">
	            <thead>
		            <tr>
		                <th style="background-color: #eeeeee; text-align: center;">지역 구</th>
		                <th style="background-color: #eeeeee; text-align: center;">지역 이름</th>
		                <th style="background-color: #eeeeee; text-align: center;">주소1</th>
		                <th style="background-color: #eeeeee; text-align: center;">주소 2</th>
		                <th style="background-color: #eeeeee; text-align: center;">위도</th>
		                <th style="background-color: #eeeeee; text-align: center;">경도</th>
		                <th style="background-color: #eeeeee; text-align: center;">북마크</th>
		            </tr>
	            </thead>
	            
	            <tbody>
	            <% 
				    WifiService wifiService = new WifiService();
			        ArrayList<Wifi> list = wifiService.getBookMarkList(member_id);
			
			        if (list != null) {
			            for (Wifi wifiDTO : list) {
			     %>
	            	<tr>
	            		<td><%=wifiDTO.getWifi_area() %></td>
	            		<td><%=wifiDTO.getWifi_main_nm() %></td>
	            		<td><%=wifiDTO.getWifi_address() %></td>
	            		<td><%=wifiDTO.getWifi_address2() %></td>
	            		<td><%=wifiDTO.getWifi_lat() %></td>
	            		<td><%=wifiDTO.getWifi_lnt() %></td>
	            		<td><button onclick="bookMarkDelete(`<%=wifiDTO.getWifi_no()%>`)">북마크 제거</button> </td>
	            	</tr>
	           	<% } %>
                <% } else { %>
                    <h1>위치 정보를 입력하신 후에 조회해 주세요.</h1>
                <% } %>
	            </tbody>
	        </table>     
        </div>
	</div>
	
</body>
</html>