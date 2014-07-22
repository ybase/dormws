<%@ page language="java" import="java.util.*,com.ybase.dorm.vo.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	DrUser user = new DrUser();
	user.setId(1);
	user.setName("test");
	request.getSession().setAttribute("loginusr", user);
%>
<!DOCTYPE html>
<html>
<head>
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript">
			//验证浏览器是否支持WebSocket协议  
			if (!window.WebSocket) {
				alert("WebSockeet not supported by this browser!");
			}
			var ws;
			function display() {
				ws = new WebSocket("ws://localhost:8082/dormws/dormws");
				//监听消息  
				ws.onmessage = function(event) {
					log(event.data);
				}
		
				//关闭WebSocket  
				ws.onclose = function(event) {
				}
				//打开WebSocket  
				ws.onopen = function(event) {
					ws.send("Hello,Server");
				}
				ws.onerror = function(event) {
				}
			}
			var log = function(s) {
				if (document.readyState !== "complete") {
					log.buffer.pust(s);
				} else {
					document.getElementById("contendId").innerHTML += (s + "\n");
				}
			}
			function sendMsg() {
				var msg = document.getElementById("messageId");
				ws.send(msg.value);
			}
		</script>
</head>
	<body onload="display();">
		<div id="valueLabel"></div>
		<textarea rows="20" cols="30" id="contendId"></textarea>
		<br />
		<input name="message" id="messageId" />
		<button id="sendButton" onClick="javascript:sendMsg()">Send</button>
	</body>
</html>
