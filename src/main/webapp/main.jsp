<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%> <!-- euc=kr > utf-8로 변경 -->
    <%@ page import="java.io.PrintWriter" %>

<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel ="stylesheet" href="css/bootstrap.css">
<title>Main</title>
<script>
function getLocation() {
	  if (navigator.geolocation) { // GPS를 지원하면
	    navigator.geolocation.getCurrentPosition(function(position) {
	    	let lati = position.coords.latitude;
	    	let longi = position.coords.longitude;
	    	document.getElementById('lat').value = lati;
	    	document.getElementById('lnt').value = longi;
	      // alert(position.coords.latitude + ' ' + position.coords.longitude);
	    }, function(error) {
	      console.error(error);
	    }, {
	      enableHighAccuracy: false,
	      maximumAge: 0,
	      timeout: Infinity
	    });
	  } else {
	    alert('GPS를 지원하지 않습니다 직접 좌표를 입력하세요');
	  }
	}
	
	function wifiUpdate(){
		window.open('http://www.yezzang.pe.kr:1005/wifiDBUpdate.jsp', "_blank","width=420,height=500,left=200,top=200")
	}
	function getNear(){
		window.open('http://www.yezzang.pe.kr:1005/wifiNearList.jsp?lat='+
				document.getElementById('lat').value+
				'&lnt='+document.getElementById('lnt').value,
				"_blank","width=1200,height=1200,left=200,top=200")
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
				<li class="active"><a href="main.jsp">메인</a></li>
				<li><a href="bookmark.jsp">북마크</a></li>
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
	<div>
		<div class="input">
	        <span>&nbspLAT:</span>
	        <input type="text" id="lat" value="">
	
	        <span>LNT:</span>
	        <input type="text" id="lnt" value="">
	
	        <button id="btn_cur_position" onclick ="getLocation()"><span>내 위치 가져오기</span></button>
	        <button id="btn_nearest_wifi" onclick ="getNear()"><span>근처 Wifi 정보 보기</span></button>
	        <button id="btn_nearest_wifi" onclick ="wifiUpdate()"><span>Wifi DB 정보 업데이트 하기</span></button>
    	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
</body>
</html>